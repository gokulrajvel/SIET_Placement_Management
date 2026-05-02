package com.gokulrajvel.placementportal.features.signin.studentsignin;

import com.gokulrajvel.placementportal.data.dto.LoginRequest;
import com.gokulrajvel.placementportal.data.dto.StudentSignup;
import com.gokulrajvel.placementportal.data.repository.PlacementSIETDB;

import static util.ParseHelper.EMAIL_PATTERN;

public class StudentSignInModel {
    private final StudentSignInPresenter studentSignInPresenter;

    public StudentSignInModel(StudentSignInPresenter studentSignInPresenter) {
        this.studentSignInPresenter = studentSignInPresenter;
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
            studentSignInPresenter.displayError("Invalid email or password");
            return;
        }
        String emailError = validateEmail(request.getEmail());
        if (emailError != null) {
            studentSignInPresenter.displayError(emailError);
            return;
        }
        String passwordError = validatePassword(request.getPassword());
        if (passwordError != null) {
            studentSignInPresenter.displayError(passwordError);
            return;
        }

        StudentSignup student = PlacementSIETDB.getInstance().authenticateStudent(
                request.getEmail(), request.getPassword());
        if (student == null) {
            studentSignInPresenter.onSignInFailed("Invalid email or password");
            return;
        }
        studentSignInPresenter.onSignInSuccessful(student);
    }
}
