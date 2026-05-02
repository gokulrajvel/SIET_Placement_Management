package com.gokulrajvel.placementportal.features.signin.studentsignin;

import com.gokulrajvel.placementportal.data.dto.LoginRequest;
import com.gokulrajvel.placementportal.data.dto.StudentSignup;

public class StudentSignInPresenter {
    private final StudentSignInView studentSignInView;
    private final StudentSignInModel studentSignInModel;

    public StudentSignInPresenter(StudentSignInView studentSignInView) {
        this.studentSignInView = studentSignInView;
        this.studentSignInModel = new StudentSignInModel(this);
    }
    void authenticate(LoginRequest loginRequest) {
        studentSignInModel.authenticate(loginRequest);
    }

    void onSignInSuccessful(StudentSignup studentSignup) {
        studentSignInView.onSignInSuccessful(studentSignup);
    }

    void onSignInFailed(String message) {
        studentSignInView.onSignInFailed(message);
    }

    void displayError(String message) {
        studentSignInView.displayError(message);
    }
}
