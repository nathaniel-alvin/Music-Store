package com.example.musicshop;

public class Branch {
    private int id;
    private String name;
    private String location;
    private int numberOfEmployee;
    private int numberOfItem;

    public Branch(int id, String name, String location, int numberOfEmployee, int numberOfItem) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.numberOfEmployee = numberOfEmployee;
        this.numberOfItem = numberOfItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfEmployee() {
        return numberOfEmployee;
    }

    public void setNumberOfEmployee(int numberOfEmployee) {
        this.numberOfEmployee = numberOfEmployee;
    }

    public int getNumberOfItem() {
        return numberOfItem;
    }

    public void setNumberOfItem(int numberOfItem) {
        this.numberOfItem = numberOfItem;
    }
}
