package com.example.musicshop;

public class Instrument {
    private int id;
    private String name;
    private String category;
    private int quantity;
    private float price;
    private String description;
    private int yop;

    public Instrument(int id, String name, String category, int quantity, float price, String description, int yop) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.yop = yop;
    }

    public Instrument(int id, String name, int quantity, float price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Instrument(String name, int quantity, float price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getYop() {
        return yop;
    }

    public void addQuantity() {
        quantity += 1;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotalPrice() {
        float totalPrice = this.quantity * this.price;
        System.out.println(this.getName() + totalPrice);
        return totalPrice;
    }
}
