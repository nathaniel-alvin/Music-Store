package com.example.musicshop;

import javafx.collections.ObservableList;


public class Singleton {
    private String username;
    private ObservableList<Instrument> list;

    private static Singleton INSTANCE = new Singleton();

    private Singleton() {};

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }
    public void setUsername(String user) {
        this.username = user;
//        System.out.println("Current username " + this.username);
    }

    public String getUsername() {
        return this.username;
    }

    public ObservableList<Instrument> getList() {
        return list;
    }

    public void setList(ObservableList<Instrument> list) {
        this.list = list;
    }
}
