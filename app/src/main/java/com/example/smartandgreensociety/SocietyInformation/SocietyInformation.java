package com.example.smartandgreensociety.SocietyInformation;

import java.util.HashMap;
import java.util.Map;

public class SocietyInformation {

    private String societyName,societySecretaryName,societyChairmanName,societyTreasurerName
            ,societyAddress,societyContact,societySecretaryContact,societyChairmanContact,
            societyTreasurerContact;
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

    public String getSocietyContact() {
        return societyContact;
    }

    public void setSocietyContact(String societyContactNumber) {
        this.societyContact = societyContactNumber;
    }

    public String getSocietyTreasurerName() {
        return societyTreasurerName;
    }

    public void setSocietyTreasurerName(String societyTreasurerName) {
        this.societyTreasurerName = societyTreasurerName;
    }

    public String getSocietySecretaryContact() {
        return societySecretaryContact;
    }

    public void setSocietySecretaryContact(String societySecretaryContact) {
        this.societySecretaryContact = societySecretaryContact;
    }

    public String getSocietyChairmanContact() {
        return societyChairmanContact;
    }

    public void setSocietyChairmanContact(String societyChairmanContact) {
        this.societyChairmanContact = societyChairmanContact;
    }

    public String getSocietyTreasurerContact() {
        return societyTreasurerContact;
    }

    public void setSocietyTreasurerContact(String societyTreasurerContact) {
        this.societyTreasurerContact = societyTreasurerContact;
    }

    public Map toSocietyInfoMap(){

        societyInfoMap.put("societyName",getSocietyName());
        societyInfoMap.put("societyContact",getSocietyContact());
        societyInfoMap.put("societySecretaryName",getSocietySecretaryName());
        societyInfoMap.put("societySecretaryContact",getSocietySecretaryContact());
        societyInfoMap.put("societyChairmanName",getSocietyChairmanName());
        societyInfoMap.put("societyChairmanContact",getSocietyChairmanContact());
        societyInfoMap.put("societyTreasurerName",getSocietyTreasurerName());
        societyInfoMap.put("societyTreasurerContact",getSocietyTreasurerContact());
        societyInfoMap.put("societyAddress",getSocietyAddress());
        return societyInfoMap;
    }
}
