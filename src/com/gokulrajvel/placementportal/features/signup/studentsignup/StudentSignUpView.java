package com.gokulrajvel.placementportal.features.signup.studentsignup;

import com.gokulrajvel.placementportal.data.dto.*;
import util.ParseHelper;

import java.util.Date;

import static util.ConsoleInput.*;

public class StudentSignUpView {
    private StudentSignUpPresenter studentSignupPresenter;

    public StudentSignUpView() {
        this.studentSignupPresenter = new StudentSignUpPresenter(this);
    }

    public void init() {
        signup();
    }

    public void signup() {
        StudentSignup studentSignUp = new StudentSignup();
        System.out.println();
        System.out.println("Student Sign Up");
        studentSignUp.setRegisterNo(readRequired("Register No: "));
        studentSignUp.settName(readRequired("Name: "));
        studentSignUp.setEmail(readEmail("Email: "));
        studentSignUp.setPassword(readPassword("Password: "));
        studentSignUp.setContactNo(readMobile("Contact No: "));
        studentSignUp.setBirthDate(readDob("DOB (" + ParseHelper.getDobPattern() + "): "));
        studentSignUp.setBatch(readRequired("Batch: "));
        studentSignUp.setSkill(readSkills());
        studentSignupPresenter.register(studentSignUp);
        //new SignupModel().registerStudent(studentSignUp);
    }

    private String[] readSkills() {
        String skills = readRequired("Skills (comma separated): ");
        String[] parts = skills.split(",");
        for (int index = 0; index < parts.length; index++) {
            parts[index] = parts[index].trim();
        }
        return parts;
    }

    private String readPassword(String label) {
        while (true) {
            System.out.printf(label);
            String passWord = scanner.nextLine().trim();
            if (studentSignupPresenter.isPasswordValid(passWord)) {
                return passWord;
            }
            error("Password must be at least 8 characters and include letters and numbers.");
        }
    }

    private String readMobile(String label) {
        while (true) {
            System.out.printf(label);
            String mobile = scanner.nextLine().trim();
            if (studentSignupPresenter.isPhoneValid(mobile)) {
                return mobile;
            }
            error("Enter a valid 10-digit mobile number starting with 6-9.");
        }
    }

    private Date readDob(String label) {
        while (true) {
            System.out.printf(label);
            String dob = scanner.nextLine().trim();
            Date dobDate = studentSignupPresenter.isDOBValid(dob);
            if (dobDate != null) {
                return dobDate;
            }
            error("Enter a valid date.");
        }
    }

    private String readEmail(String label) {
        while (true) {
            System.out.printf(label);
            String email = scanner.nextLine().trim();
            if (studentSignupPresenter.isEmailValid(email)) {
                return email;
            }
            error("Enter a valid email.");
        }
    }

    private String readRequired(String label) {
        while (true) {
            System.out.print(label);
            String value = scanner.nextLine().trim();
            if (studentSignupPresenter.isRegesterNoValidition(value)) {
                return value;
            }
            error("<<---Value cannot be empty--->>.\nReEnter your " + label + " : ");
        }
    }

    void error(String message) {
        System.out.println(message);
    }
}

