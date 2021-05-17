package com.example.finalyearproject;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.finalyearproject.utility.AddToBusketModel;
import com.example.finalyearproject.utility.firebase;

public class CustomeDialog_placeOrder extends Dialog {

    public CustomeDialog_placeOrder(@NonNull Context context , String order , int price) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_for_custom_dialog_placeorder);

        ((TextView)findViewById(R.id.placeOrder_detail)).setText(order);
        ((TextView)findViewById(R.id.placeOrder_price)).setText("Rs "+price+" + 50 delivery Chrage");
        findViewById(R.id.placeOrder_clearBacket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddToBusketModel.list.clear();
                ((TextView)findViewById(R.id.placeOrder_detail)).setText("");
                ((TextView)findViewById(R.id.placeOrder_price)).setText("");
                Toast.makeText(context, "Basket Cleared", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.placeOrder_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebase.placeOrder(order , price , context);
                dismiss();
            }
        });
    }
}
