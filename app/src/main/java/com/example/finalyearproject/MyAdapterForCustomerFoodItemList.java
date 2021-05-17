package com.example.finalyearproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.utility.AdminNewItemModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapterForCustomerFoodItemList extends RecyclerView.Adapter<MyAdapterForCustomerFoodItemList.MyViewHolder> {

    List<AdminNewItemModel> list;
    Context context;
    MyAdapterForCustomerFoodItemList.onClickListener listener;
    int itemCount = 1;

    MyAdapterForCustomerFoodItemList(List<AdminNewItemModel> list, Context context, MyAdapterForCustomerFoodItemList.onClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout_for_food_list_customer, parent, false);
        return new MyAdapterForCustomerFoodItemList.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AdminNewItemModel temp = list.get(position);
        holder.name.setText(temp.getName());
        holder.dis.setText(temp.getDis());
        holder.price.setText(temp.getPrice());
        String uri = temp.getUri();
        Picasso.get().load(uri).fit().centerCrop().into(holder.image);
        holder.itemsTV.setText(itemCount+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, dis, price, plusButton, minusButton, itemsTV;
        Button itemsNumber;
        RelativeLayout linearLayout;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.foodItemName_customer);
            dis = itemView.findViewById(R.id.foodItemDis_customer);
            price = itemView.findViewById(R.id.foodItemPrice_customer);
            itemsTV = itemView.findViewById(R.id.numberOfItemsTV);

            itemsNumber = itemView.findViewById(R.id.addToBusketButton);
            itemsNumber.setOnClickListener(this);

            plusButton = itemView.findViewById(R.id.plusButton);
            plusButton.setOnClickListener(this);

            minusButton = itemView.findViewById(R.id.minusButton);
            minusButton.setOnClickListener(this);

            linearLayout = itemView.findViewById(R.id.child_cardView_customer);
            image = itemView.findViewById(R.id.imagefff_customer);

        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.addToBusketButton)
                listener.onClick(getAdapterPosition() , itemCount);
            else if(view.getId() == R.id.plusButton){
                itemsTV.setText(++itemCount + "");
            }
            else if(view.getId() == R.id.minusButton && itemCount != 1){
                itemsTV.setText(--itemCount + "");
            }
        }
    }

    public interface onClickListener {
        void onClick(int position  , int itemCount);
    }
}
