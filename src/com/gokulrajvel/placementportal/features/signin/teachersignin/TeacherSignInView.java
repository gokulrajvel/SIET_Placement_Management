package com.gokulrajvel.placementportal.features.signin.teachersignin;

import com.gokulrajvel.placementportal.data.dto.LoginRequest;
import com.gokulrajvel.placementportal.data.dto.TeacherSignup;
import com.gokulrajvel.placementportal.features.signup.SignupView;
import com.gokulrajvel.placementportal.features.teacherportal.TeacherPortalView;
import util.ConsoleInput;

import java.util.Scanner;

public class TeacherSignInView {
    private final Scanner scanner = ConsoleInput.getScanner();
    private final TeacherSignInPresenter teacherSignInPresenter;
    private boolean authenticated;

    public TeacherSignInView() {
        this.teacherSignInPresenter = new TeacherSignInPresenter(this);
        this.authenticated = false;
    }

    public void init() {
        System.out.println();
        System.out.println("Teacher Sign-In");
        while (!authenticated) {
            promptAndAuthenticate();
            if (authenticated) {
                return;
            }
            if (!promptPostFailureAction()) {
                return;
            }
        }
    }

    private void promptAndAuthenticate() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email == null ? null : email.trim());
        loginRequest.setPassword(password);
        teacherSignInPresenter.authenticate(loginRequest);
    }

    private boolean promptPostFailureAction() {
        while (true) {
            System.out.println();
            System.out.println("1. Retry");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    return true;
                case "2":
                    new SignupView().init();
                    return false;
                case "3":
                    System.out.println("Thank you for using SIET Placement.");
                    System.exit(0);
                    return false;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    void onSignInSuccessful(TeacherSignup teacherSignup) {
        authenticated = true;
        System.out.printf("Welcome, %s!%n", teacherSignup.getTeacherId());
        new TeacherPortalView(teacherSignup).init();
    }

    void onSignInFailed(String message) {
        System.out.println(message);
    }

    void displayError(String error) {
        System.out.println(error);
    }
}
