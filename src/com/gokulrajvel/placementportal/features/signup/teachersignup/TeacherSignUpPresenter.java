package com.gokulrajvel.placementportal.features.signup.teachersignup;

import com.gokulrajvel.placementportal.data.dto.TeacherSignup;
import java.util.Date;

class TeacherSignUpPresenter {
  private TeacherSignUpView teacherSignupView;
  private TeacherSignUpModel teacherSignupModel;

  public TeacherSignUpPresenter(TeacherSignUpView teacherSignupView) {
    this.teacherSignupView = teacherSignupView;
    this.teacherSignupModel = new TeacherSignUpModel(this);
  }

  boolean isTeacherIDValid(String teacherID) {
    if (teacherSignupModel.isTeacherIDValid(teacherID)) {
      return true;
    }
    return false;
  }

  boolean isValidEmail(String email) {
    if (teacherSignupModel.isEmailValid(email)) {
      return true;
    }
    return false;
  }

  boolean isValidPassword(String password) {
    if (teacherSignupModel.isPasswordValid(password)) {
      return true;
    }
    return false;
  }

  boolean isMobileNumberValid(String mobileNumber) {
    if (teacherSignupModel.isMobileNumberValid(mobileNumber)) {
      return true;
    }
    return false;
  }

  Date isValidDOB(String dob) {
    return teacherSignupModel.isValidDOB(dob);
  }

  void registerTeacher(TeacherSignup teacherSignUp) {
    teacherSignupView.error(teacherSignupModel.registerTeacher(teacherSignUp));
  }
}
