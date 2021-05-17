package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.finalyearproject.utility.OrdersOnQueue;
import com.example.finalyearproject.utility.TakeOrderModel;
import com.example.finalyearproject.utility.firebase;

public class Employ extends AppCompatActivity implements CustomDialog_TakeOrder.onButtonClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ);

        getSupportActionBar().hide();
        ((TextView)findViewById(R.id.Employ_name)).setText(getIntent().getStringExtra("name"));
        ((TextView)findViewById(R.id.Employ_id)).setText(getIntent().getStringExtra("id"));
        setListeners();
        startService(new Intent(this , MyIntentServiceForEmploy.class));
    }

    private void setListeners(){
        ((findViewById(R.id.Employ_takeOrder_button))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog_TakeOrder temp = new CustomDialog_TakeOrder(Employ.this , Employ.this);
                temp.show();
            }
        });

        ((findViewById(R.id.Employ_ordersOnQueue))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp = new Intent(Employ.this , OrdersOnQueue.class);
                temp.putExtra("type" , "1");
                startActivity(temp);
            }
        });

        ((findViewById(R.id.Employ_onlineOrders))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp = new Intent(Employ.this , OrdersOnQueue.class);
                temp.putExtra("type" , "2");
                startActivity(temp);
            }
        });

        ((findViewById(R.id.Employ_logoutButton))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebase.employLogout(Employ.this);
                stopService(new Intent(Employ.this , MyIntentServiceForEmploy.class));
                startActivity(new Intent(Employ.this , welcomeActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onClick(String order, String tableNumber) {
        TakeOrderModel temp = new TakeOrderModel(order , tableNumber , getIntent().getStringExtra("name") , getIntent().getStringExtra("id"));
        firebase.pushOrder(temp);
    }
}