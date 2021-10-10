package com.example.letchat;

public class user {
    private String uid, name, PhoneNumber, profileImg;

    public user() {

    }

    public user(String uid, String name, String phoneNumber, String profileImg) {
        this.uid = uid;
        this.name = name;
        PhoneNumber = phoneNumber;
        this.profileImg = profileImg;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}
