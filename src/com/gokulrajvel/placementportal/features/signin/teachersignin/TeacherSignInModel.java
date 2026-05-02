package com.gokulrajvel.placementportal.features.signin.teachersignin;

import com.gokulrajvel.placementportal.data.dto.LoginRequest;
import com.gokulrajvel.placementportal.data.dto.TeacherSignup;
import com.gokulrajvel.placementportal.data.repository.PlacementSIETDB;

import static util.ParseHelper.EMAIL_PATTERN;

public class TeacherSignInModel {
    private final TeacherSignInPresenter teacherSignInPresenter;

    public TeacherSignInModel(TeacherSignInPresenter teacherSignInPresenter) {
        this.teacherSignInPresenter = teacherSignInPresenter;
    }

    String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return "Email cannot be empty";
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            return "Enter a valid email address";
        }
        return null;
    }

    String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Password cannot be empty";
        }
        return null;
    }

    void authenticate(LoginRequest request) {
        if (request == null) {
            teacherSignInPresenter.displayError("Invalid email or password");
            return;
        }
        String emailError = validateEmail(request.getEmail());
        if (emailError != null) {
            teacherSignInPresenter.displayError(emailError);
            return;
        }
        String passwordError = validatePassword(request.getPassword());
        if (passwordError != null) {
            teacherSignInPresenter.displayError(passwordError);
            return;
        }

        TeacherSignup teacher = PlacementSIETDB.getInstance().authenticateTeacher(
                request.getEmail(), request.getPassword());
        if (teacher == null) {
            teacherSignInPresenter.onSignInFailed("Invalid email or password");
            return;
        }
        teacherSignInPresenter.onSignInSuccessful(teacher);
    }
}
