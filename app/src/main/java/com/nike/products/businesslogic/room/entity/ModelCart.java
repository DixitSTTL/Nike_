package com.nike.products.businesslogic.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cartTable")
public class ModelCart {
    @PrimaryKey(autoGenerate = true)
    private int id;
    int image;
    Double price;
    String name;
    int qty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public ModelCart(int image, Double price, String name, int qty) {
        this.image = image;
        this.price = price;
        this.name = name;
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
