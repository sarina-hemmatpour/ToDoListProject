package com.example.todolistproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvTaskList=findViewById(R.id.rv_main_list);
        rvTaskList.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
        rvTaskList.setAdapter(new TaskAdapter());

        FloatingActionButton fabAdd=findViewById(R.id.fab_main_addTask);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskDialog taskDialog=new TaskDialog();
                taskDialog.show(getSupportFragmentManager() , null);
            }
        });
    }
}