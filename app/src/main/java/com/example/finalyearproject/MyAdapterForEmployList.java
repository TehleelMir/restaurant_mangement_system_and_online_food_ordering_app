package com.example.finalyearproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.utility.AdminEmployModel;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MyAdapterForEmployList extends RecyclerView.Adapter<MyAdapterForEmployList.MyViewHolder> {
    List<AdminEmployModel> list;
    Context context;
    onClickItem listener;
    MyAdapterForEmployList(List<AdminEmployModel> list , Context context , onClickItem listener){
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyAdapterForEmployList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout_for_recycleview_admin_employ , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterForEmployList.MyViewHolder holder, int position) {
        holder.name.setText("Name: "+(list.get(position)).getName());
        holder.id.setText("ID: "+(list.get(position)).getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name , id;
        Button edit , delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.employNameHere);
            id = itemView.findViewById(R.id.employIdHere);
            edit = itemView.findViewById(R.id.editEmployHere);
            edit.setOnClickListener(this);
            delete = itemView.findViewById(R.id.deleteEmployHere);
            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(listener != null)
                listener.onClick(id.getText().toString().substring(4) , view);
        }
    }

    public interface onClickItem{
        void onClick(String id , View view);
    }

}
