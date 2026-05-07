package com.gokulrajvel.placementportal.features.signin;

import com.gokulrajvel.placementportal.features.signin.studentsignin.StudentSignInView;
import com.gokulrajvel.placementportal.features.signin.teachersignin.TeacherSignInView;
import java.util.Scanner;
import util.ConsoleInput;

public class SigninView {
  private final Scanner scanner = ConsoleInput.getScanner();

  public void init() {
    while (true) {
      util.UIUtils.printMenu("Sign In", new String[] {"1. Student", "2. Teacher", "3. Back"});
      util.UIUtils.printCenterPrompt("Choose an option: ");
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
          util.UIUtils.printCenter("Invalid option. Please try again.");
      }
    }
  }
}
