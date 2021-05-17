package com.example.finalyearproject;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.example.finalyearproject.utility.firebase;


public class MyIntentServiceForEmploy extends IntentService {


    public MyIntentServiceForEmploy() {
        super("MyIntentServiceForEmploy");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        DatabaseReference reference = firebase.getAllOnlineOrder();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(MyIntentServiceForEmploy.this, "notiifiicatiion", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}