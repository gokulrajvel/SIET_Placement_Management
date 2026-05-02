package com.gokulrajvel.placementportal.features.signup.studentsignup;

import com.gokulrajvel.placementportal.data.dto.StudentSignup;

import java.util.Date;

class StudentSignUpPresenter {
    private StudentSignUpView studentSignupView;
    private StudentSignUpModel studentSignupModel;

    public StudentSignUpPresenter(StudentSignUpView studentSignupView) {
        this.studentSignupView = studentSignupView;
        this.studentSignupModel = new StudentSignUpModel(this);
    }

    boolean isRegesterNoValidition(String regesterNo) {
        return studentSignupModel.isRegesterNoValidition(regesterNo);
    }

    boolean isPasswordValid(String password) {
        return studentSignupModel.isPasswordValid(password);
    }

    boolean isEmailValid(String email) {
        return studentSignupModel.isEmailValid(email);
    }

    boolean isPhoneValid(String phone) {
        return studentSignupModel.isPhoneValid(phone);

    }

    Date isDOBValid(String dob) {
        return studentSignupModel.isDOBValid(dob);
    }

    void register(StudentSignup studentSignUp) {
        studentSignupView.error(studentSignupModel.register(studentSignUp));
    }
}
