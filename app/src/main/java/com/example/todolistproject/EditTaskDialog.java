package com.example.todolistproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class EditTaskDialog extends DialogFragment {

    private SaveEditCallBack callBack;
    private Task editTask;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callBack=(SaveEditCallBack) context;
        editTask=getArguments().getParcelable("task");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.background_dialog_task);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(requireContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.edit_dialog , null , false);
        builder.setView(view);

        TextInputEditText etTitle=view.findViewById(R.id.et_editDialog_title);
        TextInputLayout inputLayout=view.findViewById(R.id.etLayout_editDialog_title);

        etTitle.setText(editTask.getTitle());

        View btnSave=view.findViewById(R.id.btn_editDialog_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etTitle.length()>0)
                {
                    editTask.setTitle(etTitle.getText().toString().trim());
                    callBack.onSaveEditCallBack(editTask);
                    dismiss();
                }
                else {
                    inputLayout.setError("لطفا عنوان را وارد کنید");
                }
            }
        });

        return builder.create();
    }

    interface SaveEditCallBack {
        void onSaveEditCallBack(Task task);
    }

    public static EditTaskDialog newInstance(Task task) {

        Bundle args = new Bundle();
        args.putParcelable("task" , task);
        EditTaskDialog fragment = new EditTaskDialog();
        fragment.setArguments(args);
        return fragment;
    }
}
