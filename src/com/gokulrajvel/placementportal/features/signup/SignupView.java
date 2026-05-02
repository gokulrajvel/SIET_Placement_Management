package com.gokulrajvel.placementportal.features.signup;

import com.gokulrajvel.placementportal.features.signup.studentsignup.StudentSignUpView;
import com.gokulrajvel.placementportal.features.signup.teachersignup.TeacherSignUpView;
import util.ConsoleInput;

import java.util.Scanner;

public class SignupView {
    private final Scanner scanner = ConsoleInput.getScanner();

    public void init() {
        while (true) {
            System.out.println();
            System.out.println("Sign Up");
            System.out.println("1. Student");
            System.out.println("2. Teacher");
            System.out.println("3. Back");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    new StudentSignUpView().init();
                    return;
                case "2":
                    new TeacherSignUpView().init();
                    return;
                case "3":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
