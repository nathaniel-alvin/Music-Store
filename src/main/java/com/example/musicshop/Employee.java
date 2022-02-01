package com.example.musicshop;

public class Employee {
    private String name;
    private String address;
    private String dob;
    private String role;
    private String gender;
    private String phonenumber;
    private String branch;

    public Employee(String name, String address, String dob, String role, String gender, String phonenumber, String branch) {
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.role = role;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.branch = branch;
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
