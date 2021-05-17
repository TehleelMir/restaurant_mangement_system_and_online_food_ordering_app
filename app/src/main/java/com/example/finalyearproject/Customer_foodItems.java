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
import android.widget.Toast;

import com.example.finalyearproject.utility.AddToBusketModel;
import com.example.finalyearproject.utility.AdminNewItemModel;
import com.example.finalyearproject.utility.firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Customer_foodItems extends AppCompatActivity implements MyAdapterForCustomerFoodItemList.onClickListener {

    RecyclerView recyclerView;
    private List<AdminNewItemModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_food_items);

        getSupportActionBar().hide();
        setListener();
    }

    private void setListener(){
        recyclerView = findViewById(R.id.recyclerview_customerFoodItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                MyAdapterForCustomerFoodItemList temp = new MyAdapterForCustomerFoodItemList(list , Customer_foodItems.this , Customer_foodItems.this);
                recyclerView.setAdapter(temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setTitle(getIntent().getStringExtra("card"));
    }

    private void setTitle(String title){
        TextView temp = findViewById(R.id.title_for_food_items_customer);
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
    public void onClick(int position , int itemsCount) {
        AdminNewItemModel temp = list.get(position);
        //firebase.placeOrder(temp , this);
        int price = Integer.parseInt(temp.getPrice());
        AddToBusketModel.list.add(new AddToBusketModel(temp.getName() , itemsCount , price));
    }
}