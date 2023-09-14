package com.nike.products.businesslogic.room.entity;

import android.graphics.drawable.Drawable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmarkTable")
public class ModelHome {
    @PrimaryKey(autoGenerate = true)
    private int id;
    int image;
    String price;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ModelHome(int image, String price, String name) {
        this.image = image;
        this.price = price;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
