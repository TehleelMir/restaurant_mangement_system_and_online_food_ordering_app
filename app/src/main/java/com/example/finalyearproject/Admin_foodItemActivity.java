package com.example.finalyearproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.finalyearproject.utility.AdminNewItemModel;
import com.example.finalyearproject.utility.firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin_foodItemActivity extends AppCompatActivity implements MyAdapterForFoodListAdmin.onClickListener  {

    private RecyclerView recyclerView;
    private List<AdminNewItemModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_food_item);

        getSupportActionBar().hide();
        setListener();
    }

    private void setListener() {
        findViewById(R.id.addNewFoodItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp = new Intent(Admin_foodItemActivity.this , Admin_new_food_item_detail_activity.class);
                temp.putExtra("card" , getIntent().getStringExtra("card"));
                temp.putExtra("type" , 1);
                startActivity(temp);
            }
        });

        recyclerView = findViewById(R.id.recyclerview_adminFoodItems);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);


        list = new ArrayList<>();
        DatabaseReference reference = firebase.getAllFoodItems(getIntent().getStringExtra("card"));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot x : snapshot.getChildren()){
                    AdminNewItemModel temp = x.getValue(AdminNewItemModel.class);
                    list.add(temp);
                }
                MyAdapterForFoodListAdmin temp = new MyAdapterForFoodListAdmin(list , Admin_foodItemActivity.this , Admin_foodItemActivity.this);
                recyclerView.setAdapter(temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setTitle(getIntent().getStringExtra("card"));
    }

    private void setTitle(String title){
        TextView temp = findViewById(R.id.title_for_food_items_admin);
        switch (title){
            case "1":{
                temp.setText("Coffee/Tea");
                break;
            }
            case "2":{
                temp.setText("Drinks/Shakes");
                break;
            }
            case "3":{
                temp.setText("Pizza/Burgeres");
                break;
            }
            case "4":{
                temp.setText("Lunch/Dinner");
                break;
            }
            case "5":{
                temp.setText("Salad/Soup");
                break;
            }
            case "6":{
                temp.setText("Ice-Cream/Desserts");
                break;
            }
        }
    }

    @Override
    public void onClick(int position , View view) {
        AdminNewItemModel temp = list.get(position);
        if(view.getId() == R.id.foodItemEdit){
            Intent temp2 = new Intent(Admin_foodItemActivity.this , Admin_new_food_item_detail_activity.class);
            temp2.putExtra("card" , getIntent().getStringExtra("card"));
            temp2.putExtra("type" , 2);

            temp2.putExtra("name",temp.getName());
            temp2.putExtra("dis",temp.getDis());
            temp2.putExtra("price",temp.getPrice());
            temp2.putExtra("image",temp.getImage());
            temp2.putExtra("uri",temp.getUri());
            startActivity(temp2);
        }else{
            firebase.deleteFoodItem(temp.getName() , temp.getImage() , getIntent().getStringExtra("card") , this);
        }
    }
}