package com.example.finalyearproject.utility;

import android.net.Uri;

public class AdminNewItemModel {
    String name , dis , price , image ;
    String uri;

    public AdminNewItemModel() {
    }

    public AdminNewItemModel(String name, String dis, String price, String image , String uri) {
        this.name = name;
        this.dis = dis;
        this.price = price;
        this.image = image;
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
