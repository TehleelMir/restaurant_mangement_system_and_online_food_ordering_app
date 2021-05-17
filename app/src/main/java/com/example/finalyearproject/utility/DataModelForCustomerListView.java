package com.example.finalyearproject.utility;

public class DataModelForCustomerListView {
    String details;
    int imageId;

    public DataModelForCustomerListView(String details, int imageId) {
        this.details = details;
        this.imageId = imageId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
