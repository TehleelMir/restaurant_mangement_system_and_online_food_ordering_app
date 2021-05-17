package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalyearproject.utility.firebase;
import com.google.firebase.auth.FirebaseAuth;

public class admin_login_activity extends AppCompatActivity {
    EditText email , password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_activity);

        getSupportActionBar().hide();
        setClickListeners();
    }

    private void setClickListeners(){
        email = findViewById(R.id.admin_email_et);
        password = findViewById(R.id.admin_password_et);

        findViewById(R.id.admin_login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout mainLayout;
                mainLayout = (LinearLayout) findViewById(R.id.main_liearnLayout_admin_login);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);

                firebase.isAdminValid(email.getText().toString() , password.getText().toString() , admin_login_activity.this);
            }
        });

        findViewById(R.id.image_admin_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(admin_login_activity.this, "\uD83C\uDF55\uD83C\uDF5F\uD83E\uDD64", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.forgot_password_admin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(admin_login_activity.this, "then die", Toast.LENGTH_SHORT).show();
            }
        });

    }
}