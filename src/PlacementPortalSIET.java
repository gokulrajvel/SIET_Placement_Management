import com.gokulrajvel.placementportal.data.repository.PlacementSIETDB;
import com.gokulrajvel.placementportal.features.signin.SigninView;
import com.gokulrajvel.placementportal.features.signup.SignupView;
import java.sql.SQLException;
import java.util.Scanner;
import util.ConsoleInput;

public class PlacementPortalSIET {
  static Scanner scanner = ConsoleInput.getScanner();

  public static void main(String[] args) throws SQLException {
    System.out.println(
        "||-----------------------------------------------------------------------------------------||");
    System.out.println(
        "||-------------------------✨\uFE0F Welcome to SIET Placement Portal!"
            + " ✨\uFE0F-------------------------||");
    System.out.println(
        "||-----------------------------------------------------------------------------------------||");
    //        System.out.println("✨\uFE0F");
    PlacementSIETDB.createTable();
    PlacementPortalSIET.init();
  }

  private static void init() {
    while (true) {
      util.UIUtils.printMenu(null, new String[] {"1. Sign Up", "2. Sign In", "3. Exit"});
      util.UIUtils.printCenterPrompt("Choose an option: ");
      String choice = scanner.nextLine().trim();
      switch (choice) {
        case "1" -> new SignupView().init();
        case "2" -> new SigninView().init();
        case "3" -> {
          util.UIUtils.printCenter("Thank you for using Placement Monitoring! 🙂‍↕️");
          return;
        }
        default -> util.UIUtils.printCenter("Invalid option. Please try again.‼️⁉️");
      }
    }
  }
}
