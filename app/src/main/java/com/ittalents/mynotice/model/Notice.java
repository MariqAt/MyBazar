package com.ittalents.mynotice.model;

import android.text.Editable;

import java.io.Serializable;

/**
 * Created by ASUS on 18.3.2017 г..
 */

public class Notice implements Serializable{

    private String title;
    private String description;
    private String phone;
    private String price;

    public Notice(String title, String description, String phone, String price) {
        setTitle(title);
        setDescription(description);
        setPhone(phone);
        setPrice(price);
    }

    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        }
    }

    public void setDescription(String description) {
        if (description != null && !description.isEmpty()) {
            this.description = description;
        }
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.isEmpty()) {
            this.phone = phone;
        }
    }

    public void setPrice(String price) {
        if (price != null && !price.isEmpty()) {
            this.price = price;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getPrice() {
        return price;
    }


}
