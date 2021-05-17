package com.example.finalyearproject;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public class CustomDialogBoxForNewEmploy extends Dialog implements android.view.View.OnClickListener {

    Button ok , cancel;
    String name = "" , id = "" ;
    buttonEventListener listener;
    int type;
    String id2;
    Context context;

    public CustomDialogBoxForNewEmploy(@NonNull Context context , buttonEventListener listener , int type , String id) {
        super(context);
        this.listener = listener;
        this.type = type;
        id2 = id;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.okButton){
            name = ((EditText)findViewById(R.id.employName)).getText().toString();
            id = ((EditText)findViewById(R.id.employId)).getText().toString();
            if(listener != null)
                listener.onSubmit(name , id , type , id2);
        }else {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_new_employ);

        ok = findViewById(R.id.okButton);
        //cancel = findViewById(R.id.cencelButton);
        ok.setOnClickListener(this);
        //cancel.setOnClickListener(this);

        if(type == 2) {
            (findViewById(R.id.employId)).setVisibility(View.GONE);
        }

    }

    public interface buttonEventListener{
        void onSubmit(String name , String id , int type , String id2);
    }
}
