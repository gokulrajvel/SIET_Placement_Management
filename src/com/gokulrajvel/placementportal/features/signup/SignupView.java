package com.gokulrajvel.placementportal.features.signup;

import com.gokulrajvel.placementportal.features.signup.studentsignup.StudentSignUpView;
import com.gokulrajvel.placementportal.features.signup.teachersignup.TeacherSignUpView;
import java.util.Scanner;
import util.ConsoleInput;

public class SignupView {
  private final Scanner scanner = ConsoleInput.getScanner();

  public void init() {
    while (true) {
      util.UIUtils.printMenu("Sign Up", new String[] {"1. Student", "2. Teacher", "3. Back"});
      util.UIUtils.printCenterPrompt("Choose an option: ");
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
          util.UIUtils.printCenter("Invalid option. Please try again.");
      }
    }
  }
}
