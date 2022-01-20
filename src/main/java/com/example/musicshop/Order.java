package com.example.musicshop;

public class Order {
    int id;
    String address;
    String status;

    public Order(int id, String address, String status) {
        this.id = id;
        this.address = address;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }
}
