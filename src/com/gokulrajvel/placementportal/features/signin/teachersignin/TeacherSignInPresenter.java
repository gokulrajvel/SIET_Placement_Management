package com.gokulrajvel.placementportal.features.signin.teachersignin;

import com.gokulrajvel.placementportal.data.dto.LoginRequest;
import com.gokulrajvel.placementportal.data.dto.TeacherSignup;

public class TeacherSignInPresenter {
    private final TeacherSignInView teacherSignInView;
    private final TeacherSignInModel teacherSignInModel;

    public TeacherSignInPresenter(TeacherSignInView teacherSignInView) {
        this.teacherSignInView = teacherSignInView;
        this.teacherSignInModel = new TeacherSignInModel(this);
    }

    void authenticate(LoginRequest loginRequest) {
        teacherSignInModel.authenticate(loginRequest);
    }

    void onSignInSuccessful(TeacherSignup teacherSignup) {
        teacherSignInView.onSignInSuccessful(teacherSignup);
    }

    void onSignInFailed(String message) {
        teacherSignInView.onSignInFailed(message);
    }

    void displayError(String message) {
        teacherSignInView.displayError(message);
    }
}
