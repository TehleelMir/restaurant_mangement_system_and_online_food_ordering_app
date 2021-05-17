package com.example.finalyearproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.finalyearproject.utility.firebase;

public class Admin_new_food_item_detail_activity extends AppCompatActivity {

    Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_food_item_detail_activity);
        getSupportActionBar().hide();
        setlistner();
    }

    private void setlistner() {
        String oldName = "" , oldDis = "" , oldprice = "" , oldImageName = "" , uri = "";
        if(getIntent().getExtras().getInt("type") == 2) {
            oldName = getIntent().getStringExtra("name");
            oldDis = getIntent().getStringExtra("dis");
            oldprice = getIntent().getStringExtra("price");
            oldImageName = getIntent().getStringExtra("image");
            uri = getIntent().getStringExtra("uri");
            ((EditText) findViewById(R.id.foodName)).setText(oldName);
            ((EditText) findViewById(R.id.foodDiscription)).setText(oldDis);
            ((EditText) findViewById(R.id.foodPrice)).setText(oldprice);
        }


        findViewById(R.id.chooseImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp = new Intent();
                temp.setType("image/*");
                temp.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(temp , "Select Picture") , 1);
            }
        });
        findViewById(R.id.addItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText)findViewById(R.id.foodName)).getText().toString();
                String dis = ((EditText)findViewById(R.id.foodDiscription)).getText().toString();
                String price = ((EditText)findViewById(R.id.foodPrice)).getText().toString();
                String card = getIntent().getStringExtra("card");

                if(getIntent().getExtras().getInt("type") == 1)
                    firebase.addNewFoodItem(card  , name , dis , price , imageUri , Admin_new_food_item_detail_activity.this);
                else{
                    String moldName = getIntent().getStringExtra("name");
                    String moldImageName = getIntent().getStringExtra("image");
                    String muri = getIntent().getStringExtra("uri");

                    firebase.editFoodItem(card , name , dis , price , imageUri , Admin_new_food_item_detail_activity.this , moldName , moldImageName , muri);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
        }
    }
}