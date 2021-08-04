package com.example.finalyearproject.utility;

import java.util.ArrayList;
import java.util.List;

public class AddToBusketModel {
    String foodItemName;
    int count , pricePerItem;

    String order;
    int price;

    public static List<AddToBusketModel> list = new ArrayList<>();

    public AddToBusketModel(String foodItemName, int count, int pricePerItem) {
        this.foodItemName = foodItemName;
        this.count = count;
        this.pricePerItem = pricePerItem;
        //list.add(new AddToBusketModel(foodItemName , count , pricePerItem));
    }

    public AddToBusketModel(String order , int price){
        this.order = order;
        this.price = price;
    }

    public AddToBusketModel getFinalOrder(){
        String tempOrder = "";
        int tempPrice = 0;
        for(int i=0 ; i<list.size() ; i++){
            AddToBusketModel temp2 = list.get(i);
            tempOrder += temp2.getCount()+" "+temp2.getFoodItemName()+" &";
            tempPrice += temp2.getPricePerItem() * temp2.getCount();
        }
        if(list.size() > 0)
        return new AddToBusketModel(tempOrder.substring(0 , tempOrder.length()-1) , tempPrice );
        else return null;
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(int pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public AddToBusketModel(){}



}
