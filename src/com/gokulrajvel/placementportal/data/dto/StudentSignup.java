package com.gokulrajvel.placementportal.data.dto;


public class StudentSignup {
  private String registerNo;
  private String name;
  private String email;
  private String password;
  private String contactNo;
  private String[] skill;
  private String batch;
  private String birthDate;
  private PlacementStatus placementStatus = PlacementStatus.NOT_PLACED;

  public enum PlacementStatus {
    PLACED,
    NOT_PLACED;
  }

  public StudentSignup() {}

  public String getRegisterNo() {
    return registerNo;
  }

  public void setRegisterNo(String registerNo) {
    this.registerNo = registerNo;
  }

  public void setName(String name) {
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

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public PlacementStatus getPlacementStatus() {
    return placementStatus;
  }

  public void setPlacementStatus(PlacementStatus placementStatus) {
    this.placementStatus = placementStatus;
  }
}
