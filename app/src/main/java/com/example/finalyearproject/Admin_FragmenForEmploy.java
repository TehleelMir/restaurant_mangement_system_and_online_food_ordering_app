package com.example.finalyearproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.utility.AdminEmployModel;
import com.example.finalyearproject.utility.firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin_FragmenForEmploy extends Fragment implements CustomDialogBoxForNewEmploy.buttonEventListener , MyAdapterForEmployList.onClickItem {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_for_employ, container, false);

        view.findViewById(R.id.registerNewEmploy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewEmploy(view);
            }
        });

        setTheRecyclerView(view);

        return view;
    }

    private void registerNewEmploy(View view) {
        CustomDialogBoxForNewEmploy temp = new CustomDialogBoxForNewEmploy(getContext(), this , 1 , "");
        temp.show();

    }

    @Override
    public void onSubmit(String name, String id , int type , String id2) {
        if(type == 1){
            firebase.addNewEmploy(name, id, getContext());
            //Toast.makeText(getActivity(), "Empoy Added", Toast.LENGTH_SHORT).show();
        }
        else if(type == 2){
            firebase.editEmploy(id2 , name);
            //Toast.makeText(getContext(), "Empoy Removed", Toast.LENGTH_SHORT).show();
        }
    }

    private void setTheRecyclerView(View view){
        recyclerView = view.findViewById(R.id.recyclerview_adminEmploy);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);

        List<AdminEmployModel> list = new ArrayList<>();

        DatabaseReference reference = firebase.getAllEmployList();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot x : snapshot.getChildren()){
                    String id = x.getKey().toString();
                    String name = x.getValue().toString();
                    list.add(new AdminEmployModel(id , name));
                }
                MyAdapterForEmployList adapter = new MyAdapterForEmployList(list , getActivity() , Admin_FragmenForEmploy.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void onClick(String id , View view) {
        if(view.getId() == R.id.deleteEmployHere) {
            firebase.deleteEmploy(id , getContext());
        }
        else if(view.getId() == R.id.editEmployHere) {
            CustomDialogBoxForNewEmploy temp = new CustomDialogBoxForNewEmploy(getContext(), this , 2 , id);
            temp.show();
            Toast.makeText(getContext(), "employ name changed", Toast.LENGTH_SHORT).show();
        }
    }
}
