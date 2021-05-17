package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.finalyearproject.utility.firebase;

public class welcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLogin();
        setContentView(R.layout.activity_welcome);

       getSupportActionBar().hide();
        setClickListeners();
    }

    private void setClickListeners(){
        findViewById(R.id.customerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(welcomeActivity.this , Customer_login_activity.class));
            }
        });

        findViewById(R.id.employButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(welcomeActivity.this , admin_employ_activity.class));
            }
        });

        findViewById(R.id.image_welcome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(welcomeActivity.this, "\uD83C\uDF55\uD83C\uDF5F\uD83E\uDD64", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkLogin(){
        String temp = firebase.getSharedPrefFileValue(this);

        switch (temp){
            case "ADMIN":{
                startActivity(new Intent(this , admin.class));
                finish();
                break;
            }
            case "CUSTOMER":{
               startActivity(new Intent(this , Customer.class));
                break;
            }
            case "EMPLOY":{
                String temp2 = firebase.getSaveEmploy(this);
                if(!temp2.equals("NO_ONE"))
                firebase.isEmployValid(temp2 , this);
                break;
            }
            case "NO_ONE":{
            }
        }
    }
}