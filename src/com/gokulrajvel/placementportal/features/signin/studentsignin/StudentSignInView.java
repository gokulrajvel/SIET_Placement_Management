package com.gokulrajvel.placementportal.features.signin.studentsignin;

import static util.ParseHelper.isValidEmail;

import com.gokulrajvel.placementportal.data.dto.LoginRequest;
import com.gokulrajvel.placementportal.data.dto.StudentSignup;
import com.gokulrajvel.placementportal.features.signup.SignupView;
import com.gokulrajvel.placementportal.features.studentportal.StudentPortalView;
import java.util.Scanner;
import util.ConsoleInput;

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
    util.UIUtils.printCenter("|==== Sign-In to SIET Placement. ====|");
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
      System.out.println("<---------- Invalid email address!. ---------->");
      promptAndAuthenticate();
    }
    //        System.out.print();
    String password = ConsoleInput.readPassword("Enter your password: ");

    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setEmail(email == null ? null : email.trim());
    loginRequest.setPassword(password);
    studentSignInPresenter.authenticate(loginRequest);
  }

  private boolean promptPostFailureAction() {
    while (true) {
      util.UIUtils.printMenu(
          "|==== Sign In Failed! ====|", new String[] {"1. Retry", "2. Sign Up", "3. Exit"});
      util.UIUtils.printCenterPrompt("Choose an option: ");
      String choice = scanner.nextLine().trim();
      switch (choice) {
        case "1":
          return true;
        case "2":
          new SignupView().init();
          return false;
        case "3":
          util.UIUtils.printCenter("Thank you for using SIET Placement. \uD83D\uDC4B ✨\uFE0F");
          System.exit(0);
          return false;
        default:
          util.UIUtils.printCenter("Invalid option. Please try again.");
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
