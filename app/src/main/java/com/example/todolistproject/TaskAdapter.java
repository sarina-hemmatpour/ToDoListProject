package com.example.todolistproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<Task> tasks;
    private TaskItemEventListener eventListener;

    public TaskAdapter(ArrayList<Task> tasks ,TaskItemEventListener eventListener) {
        this.tasks = tasks;
        this.eventListener=eventListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bindItem(position);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{

        CheckBox chbTask;
        ImageView imgRemove;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            chbTask=itemView.findViewById(R.id.chb_taskItem_task);
            imgRemove=itemView.findViewById(R.id.img_taskItem_remove);

        }

        public void bindItem(int i){
            chbTask.setText(tasks.get(i).getTitle());
            chbTask.setChecked(tasks.get(i).isDone());
//            chbTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    eventListener.onCheckBoxClicked(tasks.get(i));
//                }
//            });
            imgRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eventListener.onDeleteButtonClicked(tasks.get(i));
                }
            });
        }

    }

    public void addTask(Task task){
        tasks.add(0,task);
        notifyItemInserted(0);
    }
//    public void editTask(Task task){
//        int pos=-1;
//        for (int i = 0; i < tasks.size(); i++) {
//            if (tasks.get(i).getId()==task.getId())
//            {
//                pos=i;
//                break;
//            }
//        }
//
//        if (pos>-1)
//        {
//            tasks.get(pos).setDone(task.isDone());
//        }
//
//        notifyItemChanged(pos);
//    }

    public void deleteTask(Task task)
    {
        int pos=-1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId()==task.getId())
            {
                pos=i;
                break;
            }
        }

        if (pos>-1)
        {
            tasks.remove(pos);
        }

        notifyItemRemoved(pos);
    }

    public interface TaskItemEventListener{
//        void onCheckBoxClicked(Task task);
        void onDeleteButtonClicked(Task task);
    }

}
