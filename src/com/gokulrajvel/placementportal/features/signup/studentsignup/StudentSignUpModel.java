package com.gokulrajvel.placementportal.features.signup.studentsignup;

import com.gokulrajvel.placementportal.data.dto.StudentSignup;
import com.gokulrajvel.placementportal.data.repository.PlacementSIETDB;
import java.util.Date;
import util.ParseHelper;

class StudentSignUpModel {
  private StudentSignUpPresenter studentSignupPresenter;

  public StudentSignUpModel(StudentSignUpPresenter studentSignupPresenter) {
    this.studentSignupPresenter = studentSignupPresenter;
  }

  boolean isRegesterNoValidition(String regesterNo) {
    if (!regesterNo.isEmpty()) {
      return true;
    }
    return false;
  }

  boolean isPasswordValid(String password) {
    if (ParseHelper.isValidPassword(password)) {
      return true;
    }
    return false;
  }

  boolean isPhoneValid(String phone) {
    if (ParseHelper.isValidMobile(phone)) {
      return true;
    }
    return false;
  }

  boolean isEmailValid(String email) {
    if (ParseHelper.isValidEmail(email)) {
      return true;
    }
    return false;
  }

  Date isDOBValid(String dob) {
    return ParseHelper.parseDate(dob);
  }

  String register(StudentSignup studentSignUp) {
    if (PlacementSIETDB.getInstance().setSignUpStudent(studentSignUp))
      return "Student Signup successful.";
    return "Student Signup failed. Please try again.";
  }
}
