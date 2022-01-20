package com.example.musicshop;

public class Category {
    private String name;
    private int quantity;

    public Category(String name, int qty) {
        this.name = name;
        this.quantity = qty;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
