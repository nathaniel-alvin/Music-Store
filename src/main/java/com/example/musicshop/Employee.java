package com.example.musicshop;

public class Employee {
    private int id;
    private String name;
    private String joinDate;
    private String address;
    private String dob;
    private String role;
    private String branch;
    private String phonenumber;
    private String gender;
    private String password;

    public Employee(int id, String name, String joinDate, String address, String dob, String role, String branch, String phonenumber, String gender, String password) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.address = address;
        this.dob = dob;
        this.role = role;
        this.branch = branch;
        this.phonenumber = phonenumber;
        this.gender = gender;
        this.password = password;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
