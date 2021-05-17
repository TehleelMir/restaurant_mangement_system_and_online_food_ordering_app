package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class admin_employ_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_employ_activity);

        getSupportActionBar().hide();
        setClickListeners();
    }

    private void setClickListeners(){
        findViewById(R.id.adminButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_employ_activity.this , admin_login_activity.class));
            }
        });

        findViewById(R.id.waiterButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_employ_activity.this , Employ_login_activity.class));
            }
        });

        findViewById(R.id.image_admin_employ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(admin_employ_activity.this, "\uD83C\uDF55\uD83C\uDF5F\uD83E\uDD64", Toast.LENGTH_SHORT).show();
            }
        });
    }
}