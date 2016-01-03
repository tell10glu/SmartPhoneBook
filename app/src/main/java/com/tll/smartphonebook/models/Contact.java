package com.tll.smartphonebook.models;

import com.tll.smartphonebook.database.DatabaseHelper;

import java.util.Comparator;

/**
 * Created by abdullahtellioglu on 12/12/15.
 */
public class Contact  {
    private int id;
    private String name;
    private String surname;
    private String homeNumber,workNumber,mobileNumber;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Contact(int id, String name, String surname, String homeNumber, String workNumber, String mobileNumber, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.homeNumber = homeNumber;
        this.workNumber = workNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }
    public Contact( String name, String surname, String homeNumber, String workNumber, String mobileNumber, String email) {

        this.name = name;
        this.surname = surname;
        this.homeNumber = homeNumber;
        this.workNumber = workNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
