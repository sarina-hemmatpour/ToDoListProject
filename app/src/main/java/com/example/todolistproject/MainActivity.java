package com.example.todolistproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements TaskDialog.SaveTaskCallBack {

    private TaskDBHelper taskDBHelper;
    private TaskAdapter adapter;
    private RecyclerView rvTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        taskDBHelper=new TaskDBHelper(this);


        // ////////////////////////////////
        rvTaskList=findViewById(R.id.rv_main_list);
        rvTaskList.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
        adapter=new TaskAdapter(taskDBHelper.getTasks(), new TaskAdapter.TaskItemEventListener() {
//            @Override
//            public void onCheckBoxClicked(Task task) {
//                task.setDone(!task.isDone());
//                int result=taskDBHelper.updateTask(task);
//                if (result>0)
//                {
//                    //edit recycler view
//                    adapter.editTask(task);
//                }
//
//            }

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
                TaskDialog taskDialog=new TaskDialog();
                taskDialog.show(getSupportFragmentManager() , null);
            }
        });
    }

    @Override
    public void onSaveCallBack(Task task) {
        long id=taskDBHelper.addTask(task);
        if (id!=-1)
        {
            task.setId(id);
        }
        adapter.addTask(task);
        rvTaskList.scrollToPosition(0);
    }
}