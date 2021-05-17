package com.example.finalyearproject.utility;

public class TakeOrderModel {
    String name , tableNumber , orderTakenByName , orderTakenById , key;

    public TakeOrderModel() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public TakeOrderModel(String name, String tableNumber, String orderTakenByName, String orderTakenById) {
        this.name = name;
        this.tableNumber = tableNumber;
        this.orderTakenByName = orderTakenByName;
        this.orderTakenById = orderTakenById;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getOrderTakenByName() {
        return orderTakenByName;
    }

    public void setOrderTakenByName(String orderTakenByName) {
        this.orderTakenByName = orderTakenByName;
    }

    public String getOrderTakenById() {
        return orderTakenById;
    }

    public void setOrderTakenById(String orderTakenById) {
        this.orderTakenById = orderTakenById;
    }
}
