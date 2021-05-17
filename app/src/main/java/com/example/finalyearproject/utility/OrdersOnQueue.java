package com.example.finalyearproject.utility;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.finalyearproject.Admin_FragmenForEmploy;
import com.example.finalyearproject.MyAdapterForEmployList;
import com.example.finalyearproject.MyAdapterForOrdersOnQueue;
import com.example.finalyearproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrdersOnQueue extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_on_queue);

        if(getIntent().getStringExtra("type").equals("1")){
            ((TextView)findViewById(R.id.title_orderOnQueue)).setText("Orders");
        }else{
            ((TextView)findViewById(R.id.title_orderOnQueue)).setText("Online Orders");
        }
        getSupportActionBar().hide();


        recyclerView = findViewById(R.id.recyclerview_ordersOnQueue);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        List<TakeOrderModel> list = new ArrayList<>();
        List<PlaceOrderModel> list2 = new ArrayList<>();

        DatabaseReference reference;

        if(getIntent().getStringExtra("type").equals("1"))
            reference = firebase.getAllOrderOnQueue();
        else
            reference = firebase.getAllOnlineOrder();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                list2.clear();
                boolean temp = getIntent().getStringExtra("type").equals("1");
                for(DataSnapshot x : snapshot.getChildren()){
                    if(temp)
                        list.add(x.getValue(TakeOrderModel.class));
                    else{
                        list2.add(x.getValue (PlaceOrderModel.class));
                    }
                }
                MyAdapterForOrdersOnQueue adapter;
                if(temp) {
                    adapter = new MyAdapterForOrdersOnQueue(list, OrdersOnQueue.this, 1 , 0);
                }
                else {
                    adapter = new MyAdapterForOrdersOnQueue(list2, OrdersOnQueue.this, 2);
                }
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}