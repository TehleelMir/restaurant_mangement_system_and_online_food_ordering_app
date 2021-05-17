package com.example.finalyearproject;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
        temp.add(new DataModelForCustomerListView("one" , 2));
        temp.add(new DataModelForCustomerListView("two" , 3));
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
    }

    private void showDialog(){
//        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
//        TextView textView = new TextView(getActivity());
        AddToBusketModel addToBusketModel = new AddToBusketModel();
        AddToBusketModel finalorder = addToBusketModel.getFinalOrder();
//        String x = finalorder.getOrder() +"   "+ finalorder.getPrice();
//        textView.setText(x);
//        dialog.setView(textView);
//        dialog.show();
        CustomeDialog_placeOrder temp = new CustomeDialog_placeOrder(getActivity() , finalorder.getOrder() , finalorder.getPrice());
        temp.show();
    }
}
