package com.gokulrajvel.placementportal.features.signup.teachersignup;

import com.gokulrajvel.placementportal.data.dto.TeacherSignup;
import java.util.Date;
import java.util.Scanner;
import util.ConsoleInput;
import util.ParseHelper;

public class TeacherSignUpView {
  private TeacherSignUpPresenter teacherSignupPresenter;
  private final Scanner scanner = ConsoleInput.getScanner();

  public TeacherSignUpView() {
    this.teacherSignupPresenter = new TeacherSignUpPresenter(this);
  }

  public void init() {
    TeacherSignup teacherSignUp = new TeacherSignup();
    System.out.println();
    util.UIUtils.printCenter("Teacher Sign Up");
    teacherSignUp.setTeacherId(readRequired("Teacher Id: "));
    teacherSignUp.setName(readRequired("Teacher Name: "));
    teacherSignUp.setEmail(readEmail("Email: "));
    teacherSignUp.setPassword(readPassword("Password: "));
    teacherSignUp.setPhoneNo(readMobile("Phone No: "));
    teacherSignUp.setBirthDate(readDob("DOB (" + ParseHelper.getDobPattern() + "): "));
    teacherSignupPresenter.registerTeacher(teacherSignUp);
  }

  private String readDob(String label) {
    while (true) {
      System.out.print(label);
      String date = scanner.nextLine().trim();
      Date val = teacherSignupPresenter.isValidDOB(date);
      if (val != null) {
        return date;
      }
      error("Invalid DOB");
    }
  }

  private String readRequired(String label) {
    while (true) {
      System.out.print(label);
      String value = scanner.nextLine().trim();
      if (teacherSignupPresenter.isTeacherIDValid(value)) {
        return value;
      }
      error("<<---Value cannot be empty--->>.\nReEnter your " + label + " : ");
    }
  }

  private String readEmail(String label) {
    while (true) {
      System.out.print(label);
      String value = scanner.nextLine().trim();
      if (teacherSignupPresenter.isValidEmail(value)) {
        return value;
      }
      error("Enter Valid Email");
    }
  }

  private String readPassword(String label) {
    while (true) {
      // System.out.print(label);
      String value = ConsoleInput.readPassword(label);
      if (teacherSignupPresenter.isValidPassword(value)) {
        return value;
      }
      error("Password must be at least 8 characters and include letters and numbers.");
    }
  }

  private String readMobile(String label) {
    while (true) {
      System.out.print(label);
      String value = scanner.nextLine().trim();
      if (teacherSignupPresenter.isMobileNumberValid(value)) {
        return value;
      }
      error("Enter Valid Mobile Number");
    }
  }

  void error(String message) {
    System.out.println(message);
  }
}
