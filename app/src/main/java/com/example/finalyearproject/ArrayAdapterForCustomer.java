package com.example.finalyearproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.utility.DataModelForCustomerListView;

import java.util.ArrayList;

public class ArrayAdapterForCustomer extends RecyclerView.Adapter<ArrayAdapterForCustomer.MyViewHolder>{

    ArrayList<DataModelForCustomerListView> list;
    Context context;

    public ArrayAdapterForCustomer(ArrayList<DataModelForCustomerListView> list , Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_layout_for_listview_customer , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(position == 0)
            holder.imageView.setBackground(context.getResources().getDrawable(R.drawable.imagone));
        else if(position == 1)
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.imagetwo));
        else if(position == 2)
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.imagethree));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}



//public class ArrayAdapterForCustomer extends ArrayAdapter<DataModelForCustomerListView>{
//    private ArrayList<DataModelForCustomerListView> dataSet;
//    Context mContext;
//
//    public ArrayAdapterForCustomer(ArrayList<DataModelForCustomerListView> data, Context context) {
//        super(context, R.layout.row_layout_for_listview_customer, data);
//        this.dataSet = data;
//        this.mContext=context;
//
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(getContext());
//        convertView = inflater.inflate(R.layout.row_layout_for_listview_customer, parent, false);
//        return convertView;
//    }
//}


