package com.example.todolistproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddTaskDialog.SaveTaskCallBack  , EditTaskDialog.SaveEditCallBack {

    private TaskDBHelper taskDBHelper;
    private TaskAdapter adapter;
    private RecyclerView rvTaskList;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        taskDBHelper=new TaskDBHelper(this);


        // ////////////////////////////////
        rvTaskList=findViewById(R.id.rv_main_list);
        rvTaskList.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
        adapter=new TaskAdapter(taskDBHelper.getTasks(), new TaskAdapter.TaskItemEventListener() {
            @Override
            public void onCheckBoxClicked(Task task) {
                task.setDone(!task.isDone()); //it edits the whole list!!!!!
                int result=taskDBHelper.updateTask(task);

//                if (result>0)
//                {
//                    //edit recycler view
//                    adapter.editTask(task);
//                }

            }

            @Override
            public void onItemLongPressed(Task task) {
                EditTaskDialog editTaskDialog=EditTaskDialog.newInstance(task);
                editTaskDialog.show(getSupportFragmentManager() , null);
            }

            @Override
            public void onDeleteButtonClicked(Task task) {
                int result=taskDBHelper.deleteTask(task);
                if (result>0)
                {
                    adapter.deleteTask(task);
                }
            }
        });
        rvTaskList.setAdapter(adapter);

        FloatingActionButton fabAdd=findViewById(R.id.fab_main_addTask);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTaskDialog addTaskDialog =new AddTaskDialog();
                addTaskDialog.show(getSupportFragmentManager() , null);
            }
        });

        View btnClear=findViewById(R.id.btn_main_clearAll);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskDBHelper.clearAllTasks();
                adapter.clearList();
            }
        });

        //search
        EditText etSearch=findViewById(R.id.et_main_search);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (etSearch.length()>0)
                {
                    ArrayList<Task> tasks=taskDBHelper.searchInTasks(etSearch.getText().toString().trim());
                    adapter.setTasks(tasks);
                }
                else
                {
                    adapter.setTasks(taskDBHelper.getTasks());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onSaveCallBack(Task task) {
        long id=taskDBHelper.addTask(task);
        if (id!=-1)
        {
            task.setId(id);
            adapter.addTask(task);
        }
        else {
            Log.e(TAG, "add Error" );
        }

    }

    @Override
    public void onSaveEditCallBack(Task task) {
        int result=taskDBHelper.updateTask(task);
        if (result>0)
        {
            adapter.editTask(task);
        }
        else {
            Log.e(TAG, "Edit Error" );
        }
    }
}