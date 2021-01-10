package com.example.smartandgreensociety.SocietyInformation;

import java.util.HashMap;
import java.util.Map;

public class SocietyInformation {

    private String societyName,societySecretaryName,societyChairmanName,societyAddress,
            societyContactNumber;
    private Map<String,Object> societyInfoMap = new HashMap<>();

    public SocietyInformation(){

    }
    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getSocietySecretaryName() {
        return societySecretaryName;
    }

    public void setSocietySecretaryName(String societySecretaryName) {
        this.societySecretaryName = societySecretaryName;
    }

    public String getSocietyChairmanName() {
        return societyChairmanName;
    }

    public void setSocietyChairmanName(String societyChairmanName) {
        this.societyChairmanName = societyChairmanName;
    }

    public String getSocietyAddress() {
        return societyAddress;
    }

    public void setSocietyAddress(String societyAddress) {
        this.societyAddress = societyAddress;
    }

    public String getSocietyContactNumber() {
        return societyContactNumber;
    }

    public void setSocietyContactNumber(String societyContactNumber) {
        this.societyContactNumber = societyContactNumber;
    }

    public Map toSocietyInfoMap(){

        societyInfoMap.put("societyName",getSocietyName());
        societyInfoMap.put("societySecretaryName",getSocietySecretaryName());
        societyInfoMap.put("societyChairmanName",getSocietyChairmanName());
        societyInfoMap.put("societyContactNumber",getSocietyContactNumber());
        societyInfoMap.put("societyAddress",getSocietyAddress());
        return societyInfoMap;
    }
}
