package com.example.finalyearproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.utility.PlaceOrderModel;
import com.example.finalyearproject.utility.TakeOrderModel;
import com.example.finalyearproject.utility.firebase;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapterForOrdersOnQueue extends RecyclerView.Adapter<MyAdapterForOrdersOnQueue.MyViewHolder> {

    List<TakeOrderModel> list;
    List<PlaceOrderModel> list2;
    Context context;
    int type;

    public MyAdapterForOrdersOnQueue(List<TakeOrderModel> list , Context context , int type , int x){
        this.list = list;
        this.context = context;
        this.type = type;
    }

    public MyAdapterForOrdersOnQueue(List<PlaceOrderModel> list , Context context , int type){
        this.list2 = list;
        this.context = context;
        this.type = type;
    }
    
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(type == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.row_layout_for_orders_on_queue, parent, false);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.row_layout_for_online_orders_queue, parent, false);
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(type == 1) {
            TakeOrderModel temp = list.get(position);
            holder.orderDetail.setText(temp.getName() + "\nTable No: " + temp.getTableNumber());
            String temp2 = "Order taken By\n" + temp.getOrderTakenByName() + "\nID: " + temp.getOrderTakenById();
            holder.orderTakenBy.setText(temp2);
        }else{
            PlaceOrderModel temp = list2.get(position);
            holder.orderName.setText(temp.getOrderName());
            holder.orderPrice.setText("Rs: "+temp.getOrderPrice());
            holder.orderFrom.setText(temp.getCustomerName());
            holder.orderNumbe.setText(temp.getCustomerNumber());
            holder.orderEmail.setText(temp.getCustomerEmail());
            holder.orderAddress.setText(temp.getCustomerAddress());
        }
    }

    @Override
    public int getItemCount() {
        if(type == 1) {
            return list.size();
        }else{
            return list2.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView orderDetail , orderTakenBy;
        Button orderDone;
        
        TextView orderName , orderPrice , orderFrom , orderAddress , orderNumbe , orderEmail;
        Button orderDone2;
        
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            
            if(type == 1) {
                orderDetail = itemView.findViewById(R.id.Employ_orderDetails);
                orderTakenBy = itemView.findViewById(R.id.Employ_orderTakenBy);
                orderDone = itemView.findViewById(R.id.Employ_orderDone);
                orderDone.setOnClickListener(this);
            }else{
                orderName = itemView.findViewById(R.id.order_name);
                orderPrice = itemView.findViewById(R.id.order_price);
                orderAddress = itemView.findViewById(R.id.order_PersonAddress);
                orderFrom = itemView.findViewById(R.id.order_PersonName);
                orderNumbe = itemView.findViewById(R.id.order_PersonNumber);
                orderEmail = itemView.findViewById(R.id.order_PersonEmail);
                orderDone2 = itemView.findViewById(R.id.order_done);
                orderDone2.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.Employ_orderDone) {
                TakeOrderModel temp = list.get(getAdapterPosition());
                firebase.orderDone(temp.getKey());
            }else{
                Toast.makeText(context, "send message back to the customer", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
