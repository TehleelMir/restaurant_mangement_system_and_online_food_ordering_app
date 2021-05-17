package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.finalyearproject.utility.CustomerDetail_model;
import com.example.finalyearproject.utility.firebase;

public class Customer_registerANewAccount_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register_a_new_account);

        getSupportActionBar().hide();
        setListener();
    }

    private void setListener(){
        findViewById(R.id.Customer_register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText)findViewById(R.id.Customer_register_name)).getText().toString();
                String email = ((EditText)findViewById(R.id.Customer_register_email)).getText().toString();
                String address = ((EditText)findViewById(R.id.Customer_register_address)).getText().toString();
                String password = ((EditText)findViewById(R.id.Customer_register_password)).getText().toString();
                String Cpassword = ((EditText)findViewById(R.id.Customer_register_confirmPassword)).getText().toString();
                String number = ((EditText)findViewById(R.id.Customer_register_number)).getText().toString();

                CustomerDetail_model temp = new CustomerDetail_model(name , email , address , "customer" , number);
                firebase.registerNewCustomer(email , password , temp , Customer_registerANewAccount_activity.this);
            }
        });
    }
}