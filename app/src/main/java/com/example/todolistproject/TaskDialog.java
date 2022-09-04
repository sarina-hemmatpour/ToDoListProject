package com.example.todolistproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class TaskDialog extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.background_dialog_task);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private SaveTaskCallBack callBack;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callBack=(SaveTaskCallBack) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(requireContext());
        View view=LayoutInflater.from(getContext()).inflate(R.layout.dialog , null , false);
        builder.setView(view);

        TextInputEditText etTitle=view.findViewById(R.id.et_dialog_title);
        TextInputLayout inputLayout=view.findViewById(R.id.etLayout_dialog_title);

        View btnSave=view.findViewById(R.id.btn_dialog_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etTitle.length()>0)
                {
                    callBack.onSaveCallBack(new Task(etTitle.getText().toString().trim() , false));
                    dismiss();
                }
                else {
                    inputLayout.setError("لطفا هنوان را وارد کنید");
                }
            }
        });

        return builder.create();
    }

    interface SaveTaskCallBack{
        void onSaveCallBack(Task task);
    }



}
