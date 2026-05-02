package com.gokulrajvel.placementportal.features.signin;

import com.gokulrajvel.placementportal.features.signin.studentsignin.StudentSignInView;
import com.gokulrajvel.placementportal.features.signin.teachersignin.TeacherSignInView;
import util.ConsoleInput;

import java.util.Scanner;

public class SigninView {
    private final Scanner scanner = ConsoleInput.getScanner();

    public void init() {
        while (true) {
            System.out.println();
            System.out.println("Sign In");
            System.out.println("1. Student");
            System.out.println("2. Teacher");
            System.out.println("3. Back");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    new StudentSignInView().init();
                    return;
                case "2":
                    new TeacherSignInView().init();
                    return;
                case "3":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
