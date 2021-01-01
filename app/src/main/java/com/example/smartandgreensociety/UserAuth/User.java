package com.example.smartandgreensociety.UserAuth;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {

    private String name;
    private String email;
    private String designation;
    private String phone;
    private String uid;
    private String societyRef; //firestore provided society id

    private Map<String,Object> userMap = new HashMap<>();

    public User() {
    }

    public String getSocietyRef() {
        return societyRef;
    }

    public void setSocietyRef(String societyRef) {
        this.societyRef = societyRef;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Map toMapSecretary(){

        userMap.put("name",this.name);
        userMap.put("email",this.email);
        userMap.put("designation",this.designation);
        userMap.put("phone",this.phone);
        userMap.put("societyRef",this.societyRef);
        return userMap;

    }

    public Map toMapResident(){

        userMap.put("name",this.name);
        userMap.put("email",this.email);
        userMap.put("designation",this.designation);
        userMap.put("phone",this.phone);
        userMap.put("societyRef",this.societyRef);
        return userMap;

    }

}
