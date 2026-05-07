package com.gokulrajvel.placementportal.features.signup.teachersignup;

import static util.ParseHelper.*;

import com.gokulrajvel.placementportal.data.dto.TeacherSignup;
import com.gokulrajvel.placementportal.data.repository.PlacementSIETDB;
import java.util.Date;
import util.ParseHelper;

class TeacherSignUpModel {
  private TeacherSignUpPresenter teacherSignupPresenter;

  public TeacherSignUpModel(TeacherSignUpPresenter teacherSignupPresenter) {
    this.teacherSignupPresenter = teacherSignupPresenter;
  }

  boolean isTeacherIDValid(String teacherID) {
    if (teacherID != null && !teacherID.isEmpty()) {
      return true;
    }
    return false;
  }

  boolean isEmailValid(String email) {
    if (isValidEmail(email)) {
      return true;
    }
    return false;
  }

  boolean isPasswordValid(String password) {
    if (isValidPassword(password)) {
      return true;
    }
    return false;
  }

  boolean isMobileNumberValid(String mobileNumber) {
    if (isValidMobile(mobileNumber)) {
      return true;
    }
    return false;
  }

  Date isValidDOB(String dob) {
    Date dobDate = ParseHelper.parseDate(dob);
    return dobDate;
  }

  public String registerTeacher(TeacherSignup teacherSignUp) {
    if (PlacementSIETDB.getInstance().setSignUpTeachers(teacherSignUp))
      return "Teacher Signup successful.";
    return "Teacher Signup failed. Please try again.";
  }
}
