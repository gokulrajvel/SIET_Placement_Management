package com.gokulrajvel.placementportal.data.dto;

import java.util.Date;

public class StudentSignup {
    private String registerNo;
    private String name;
    private String email;
    private String password;
    private String contactNo;
    private String[] skill;
    private String batch;
    private Date birthDate;
    private placeMentStatus placementStatus = placeMentStatus.NOT_PLACED;
    public enum placeMentStatus {
        PLACED, NOT_PLACED;
    }

    public StudentSignup() {
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public void settName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setSkill(String[] skill) {
        this.skill = skill;
    }

    public String[] getSkill() {
        return skill;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBatch() {
        return batch;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public placeMentStatus getPlacementStatus() {
        return placementStatus;
    }

    public void setPlacementStatus(placeMentStatus placementStatus) {
        this.placementStatus = placementStatus;
    }
}
