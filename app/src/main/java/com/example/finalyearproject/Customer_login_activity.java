package com.example.finalyearproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalyearproject.utility.firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class Customer_login_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        getSupportActionBar().hide();
        setListeners();
    }

    private void setListeners(){
        findViewById(R.id.Customer_login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((EditText)findViewById(R.id.Customer_login_email)).getText().toString();
                String password = ((EditText)findViewById(R.id.Customer_login_password)).getText().toString();
                firebase.isCustomerValid(email , password , Customer_login_activity.this);
            }
        });

        findViewById(R.id.Customer_forgotPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLink();
            }
        });

        findViewById(R.id.Customer_registerNewAccount_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Customer_login_activity.this , Customer_registerANewAccount_activity.class));
            }
        });

        findViewById(R.id.image_customer_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Customer_login_activity.this, "\uD83C\uDF55\uD83C\uDF5F\uD83E\uDD64", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendLink(){
        final EditText temp = new EditText(this);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Reset Password");
        dialog.setMessage("Enter your email to receive reset link");
        dialog.setView(temp);

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String mail = temp.getText().toString();
                FirebaseAuth auth = firebase.resetPassword();
                auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Customer_login_activity.this, "reseet link send to your password", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Customer_login_activity.this, "errrorrrrrrrrrr", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        dialog.show();
    }
}