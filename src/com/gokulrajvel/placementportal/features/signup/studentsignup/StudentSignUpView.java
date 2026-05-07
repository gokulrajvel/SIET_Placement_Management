package com.gokulrajvel.placementportal.features.signup.studentsignup;

import static util.ConsoleInput.*;

import com.gokulrajvel.placementportal.data.dto.*;
import util.ConsoleInput;
import util.ParseHelper;

public class StudentSignUpView {
  private StudentSignUpPresenter studentSignupPresenter;

  public StudentSignUpView() {
    this.studentSignupPresenter = new StudentSignUpPresenter(this);
  }

  public void init() {
    signup();
  }

  public void signup() {
    StudentSignup studentSignUp = new StudentSignup();
    System.out.println();
    util.UIUtils.printCenter("Student Sign Up");
    studentSignUp.setRegisterNo(readRequired("Register No: "));
    studentSignUp.settName(readRequired("Name: "));
    studentSignUp.setEmail(readEmail("Email: "));
    studentSignUp.setPassword(readPassword("Password: "));
    studentSignUp.setContactNo(readMobile("Contact No: "));
    studentSignUp.setBirthDate(readDob("DOB (" + ParseHelper.getDobPattern() + "): "));
    studentSignUp.setBatch(readRequired("Batch: "));
    studentSignUp.setSkill(readSkills());
    studentSignupPresenter.register(studentSignUp);
    // new SignupModel().registerStudent(studentSignUp);
  }

  private String[] readSkills() {
    String skills = readRequired("Skills (comma separated): ");
    String[] parts = skills.split(",");
    for (int index = 0; index < parts.length; index++) {
      parts[index] = parts[index].trim();
    }
    return parts;
  }

  private String readPassword(String label) {
    while (true) {
      // System.out.printf(label);
      String passWord = ConsoleInput.readPassword(label);
      if (studentSignupPresenter.isPasswordValid(passWord)) {
        return passWord;
      }
      error("Password must be at least 8 characters and include letters and numbers.");
    }
  }

  private String readMobile(String label) {
    while (true) {
      System.out.printf(label);
      String mobile = scanner.nextLine().trim();
      if (studentSignupPresenter.isPhoneValid(mobile)) {
        return mobile;
      }
      error("Enter a valid 10-digit mobile number starting with 6-9.");
    }
  }

  private String readDob(String label) {
    while (true) {
      System.out.printf(label);
      String dob = scanner.nextLine().trim();
      if (studentSignupPresenter.isDOBValid(dob) != null) {
        return dob;
      }
      error("Enter a valid date.");
    }
  }

  private String readEmail(String label) {
    while (true) {
      System.out.printf(label);
      String email = scanner.nextLine().trim();
      if (studentSignupPresenter.isEmailValid(email)) {
        return email;
      }
      error("Enter a valid email.");
    }
  }

  private String readRequired(String label) {
    while (true) {
      System.out.print(label);
      String value = scanner.nextLine().trim();
      if (studentSignupPresenter.isRegesterNoValidition(value)) {
        return value;
      }
      error("<<---Value cannot be empty--->>.\nReEnter your " + label + " : ");
    }
  }

  void error(String message) {
    System.out.println(message);
  }
}
