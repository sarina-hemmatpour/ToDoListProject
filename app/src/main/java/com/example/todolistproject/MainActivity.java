package com.example.todolistproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvTaskList=findViewById(R.id.rv_main_list);
        rvTaskList.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
        rvTaskList.setAdapter(new TaskAdapter());
    }
}