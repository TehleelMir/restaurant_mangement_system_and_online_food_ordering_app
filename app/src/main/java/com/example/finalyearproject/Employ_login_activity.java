package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalyearproject.utility.firebase;

public class Employ_login_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ_login);

        getSupportActionBar().hide();
        findViewById(R.id.Employ_login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = ((EditText)findViewById(R.id.Employ_login_id)).getText().toString();
                firebase.isEmployValid(id , Employ_login_activity.this);
            }
        });

        findViewById(R.id.image_admin_employ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Employ_login_activity.this, "\uD83C\uDF55\uD83C\uDF5F\uD83E\uDD64", Toast.LENGTH_SHORT).show();
            }
        });
    }
}