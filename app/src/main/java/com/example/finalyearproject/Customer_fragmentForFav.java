package com.example.finalyearproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalyearproject.utility.AddToBusketModel;
import com.example.finalyearproject.utility.DataModelForCustomerListView;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

public class Customer_fragmentForFav extends Fragment {
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_fragment_fav , container , false);
        setListView(view);
        return view;
    }

    private void setListView(View view) {
        ArrayList<DataModelForCustomerListView> temp = new ArrayList<>();
        temp.add(new DataModelForCustomerListView("one" , 1));
        temp.add(new DataModelForCustomerListView("two" , 2));
        temp.add(new DataModelForCustomerListView("three" , 3));

        DiscreteScrollView scrollView = view.findViewById(R.id.picker);
        scrollView.setAdapter( new ArrayAdapterForCustomer(temp , getActivity()) );

        view.findViewById(R.id.basketButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
//        listView = view.findViewById(R.id.myListView);
//        listView.setAdapter(new ArrayAdapterForCustomer(temp , getActivity()));

        view.findViewById(R.id.order_on_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7006481175"));
                startActivity(intent);
            }
        });

        view.findViewById(R.id.bookATable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext() , "Firebase Database: Your Firebase Free Storage is full!" , Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showDialog(){

        AddToBusketModel addToBusketModel = new AddToBusketModel();
        AddToBusketModel finalorder = addToBusketModel.getFinalOrder();

        if(finalorder == null) {
            Toast.makeText(getContext(), "Basket is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        CustomeDialog_placeOrder temp = new CustomeDialog_placeOrder(getActivity() , finalorder.getOrder() , finalorder.getPrice());
        temp.show();
    }
}
