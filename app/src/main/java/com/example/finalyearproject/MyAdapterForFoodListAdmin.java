package com.example.finalyearproject;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.utility.AdminNewItemModel;
import com.example.finalyearproject.utility.firebase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapterForFoodListAdmin extends RecyclerView.Adapter<MyAdapterForFoodListAdmin.MyViewHolder> {

    List<AdminNewItemModel> list;
    Context context;
    onClickListener listener;

    MyAdapterForFoodListAdmin(List<AdminNewItemModel> list , Context context , onClickListener listener){
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout_for_food_list_admin , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AdminNewItemModel temp = list.get(position);
        holder.name.setText(temp.getName());
        holder.dis.setText(temp.getDis());
        holder.price.setText(temp.getPrice());
        String uri = temp.getUri();
        Picasso.get().load(uri).fit().centerCrop().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name , dis , price;
        Button edit , delete;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.foodItemName);
            dis = itemView.findViewById(R.id.foodItemDis);
            price = itemView.findViewById(R.id.foodItemPrice);
            edit = itemView.findViewById(R.id.foodItemEdit);
            edit.setOnClickListener(this);
            delete = itemView.findViewById(R.id.foodItemDelete);
            delete.setOnClickListener(this);
            image = itemView.findViewById(R.id.imagefff);
        }

        @Override
        public void onClick(View view) {
            if(listener != null)
                listener.onClick(getAdapterPosition() , view);
        }
    }

    public interface onClickListener{
        void onClick(int position , View view);
    }
}
