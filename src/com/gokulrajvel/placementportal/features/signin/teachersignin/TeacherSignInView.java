package com.gokulrajvel.placementportal.features.signin.teachersignin;

import com.gokulrajvel.placementportal.data.dto.LoginRequest;
import com.gokulrajvel.placementportal.data.dto.TeacherSignup;
import com.gokulrajvel.placementportal.features.signup.SignupView;
import com.gokulrajvel.placementportal.features.teacherportal.TeacherPortalView;
import java.util.Scanner;
import util.ConsoleInput;

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
    util.UIUtils.printCenter("Teacher Sign-In");
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
    // System.out.print();
    String password = ConsoleInput.readPassword("Enter your password: ");

    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setEmail(email == null ? null : email.trim());
    loginRequest.setPassword(password);
    teacherSignInPresenter.authenticate(loginRequest);
  }

  private boolean promptPostFailureAction() {
    while (true) {
      util.UIUtils.printMenu(null, new String[] {"1. Retry", "2. Sign Up", "3. Exit"});
      util.UIUtils.printCenterPrompt("Choose an option: ");
      String choice = scanner.nextLine().trim();
      switch (choice) {
        case "1":
          return true;
        case "2":
          new SignupView().init();
          return false;
        case "3":
          util.UIUtils.printCenter("Thank you for using SIET Placement.");
          System.exit(0);
          return false;
        default:
          util.UIUtils.printCenter("Invalid option. Please try again.");
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
