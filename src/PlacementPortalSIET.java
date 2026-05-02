import com.gokulrajvel.placementportal.data.repository.PlacementSIETDB;
import com.gokulrajvel.placementportal.features.signin.SigninView;
import com.gokulrajvel.placementportal.features.signup.SignupView;
import util.ConsoleInput;

import java.util.Scanner;

public class PlacementPortalSIET {
    static Scanner scanner = ConsoleInput.getScanner();

    public static void main(String[] args) {
        System.out.println("||-----------------------------------------------------------------------------------------||");
        System.out.println("||--------------------------- Welcome to SIET Placement Portal! ---------------------------||");
        System.out.println("||-----------------------------------------------------------------------------------------||");
        PlacementPortalSIET.init();
    }

    private static void init() {
        while (true) {
            System.out.println();
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> new SignupView().init();
                case "2" -> new SigninView().init();
                case "3" -> {
                    System.out.println("Thank you for using Placement Monitoring!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

}
