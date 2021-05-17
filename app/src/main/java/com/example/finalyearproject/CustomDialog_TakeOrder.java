package com.example.finalyearproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.finalyearproject.utility.TakeOrderModel;
import com.example.finalyearproject.utility.firebase;

public class CustomDialog_TakeOrder extends Dialog {
    onButtonClick listener;

    public CustomDialog_TakeOrder(@NonNull Context context , onButtonClick listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_take_order);

        findViewById(R.id.TakeOrder_pushOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order();
            }
        });
    }

    private void order(){
        String whatsOrder = ((EditText)findViewById(R.id.TakeOrder_order)).getText().toString();
        String tableNumber = ((EditText)findViewById(R.id.TakeOrder_tableNumber)).getText().toString();
        if(listener!=null){
            listener.onClick(whatsOrder , tableNumber);
        }

    }

    public interface onButtonClick{
        void onClick(String order , String tableNumber);
    }
}
