package com.example.finalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class Customer_fragmentForItems extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_fragment_items , container , false);

        setListener(view);

        return view;
    }

    private void setListener(View view) {
        CardView temp = view.findViewById(R.id.card1_customer);
        temp.setOnClickListener(this);

        temp = view.findViewById(R.id.card2_customer);
        temp.setOnClickListener(this);

        temp = view.findViewById(R.id.card3_customer);
        temp.setOnClickListener(this);

        temp = view.findViewById(R.id.card4_customer);
        temp.setOnClickListener(this);

        temp = view.findViewById(R.id.card5_customer);
        temp.setOnClickListener(this);

        temp = view.findViewById(R.id.card6_customer);
        temp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent temp = new Intent(getContext() , Customer_foodItems.class);
        switch (view.getId()){
            case R.id.card1_customer:{
                temp.putExtra("card" , "1");
                break;
            }
            case R.id.card2_customer:{
                temp.putExtra("card", "2");
                break;
            }
            case R.id.card3_customer:{
                temp.putExtra("card", "3");
                break;
            }
            case R.id.card4_customer:{
                temp.putExtra("card", "4");
                break;
            }
            case R.id.card5_customer:{
                temp.putExtra("card", "5");
                break;
            }
            case R.id.card6_customer:{
                temp.putExtra("card", "6");
                break;
            }
        }
        startActivity(temp);
    }
}
