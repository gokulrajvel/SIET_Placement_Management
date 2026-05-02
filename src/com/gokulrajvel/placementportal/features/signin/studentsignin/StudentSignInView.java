package com.gokulrajvel.placementportal.features.signin.studentsignin;

import com.gokulrajvel.placementportal.data.dto.LoginRequest;
import com.gokulrajvel.placementportal.data.dto.StudentSignup;
import com.gokulrajvel.placementportal.features.studentportal.StudentPortalView;
import util.ConsoleInput;
import com.gokulrajvel.placementportal.features.signup.SignupView;

import java.util.Scanner;

import static util.ParseHelper.isValidEmail;

public class StudentSignInView {
    private final Scanner scanner = ConsoleInput.getScanner();
    private final StudentSignInPresenter studentSignInPresenter;
    private boolean authenticated;

    public StudentSignInView() {
        this.studentSignInPresenter = new StudentSignInPresenter(this);
        authenticated = false;
    }

    public void init() {
        System.out.println();
        System.out.println("Sign-In to SIET Placement.");
        while (!authenticated) {
            promptAndAuthenticate();
            if (authenticated) return;
            if (!promptPostFailureAction()) return;
        }
    }

    private void promptAndAuthenticate() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        if (!isValidEmail(email)) {
            System.out.println("Invalid email address");
            promptAndAuthenticate();
        }
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        LoginRequest request = new LoginRequest();
        request.setEmail(email == null ? null : email.trim());
        request.setPassword(password);
        studentSignInPresenter.authenticate(request);
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

    void onSignInSuccessful(StudentSignup studentSignup) {
        authenticated = true;
        System.out.printf("Welcome, %s!%n", studentSignup.getName());
        new StudentPortalView(studentSignup).init();
    }

    void onSignInFailed(String message) {
        System.out.println(message);
    }

    void displayError(String error) {
        System.out.println(error);
    }
}
