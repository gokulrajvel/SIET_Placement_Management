package util;

public class UIUtils {
  private static final int TERMINAL_WIDTH = 93;

  public static void printCenter(String text) {
    if (text == null || text.isEmpty()) {
      System.out.println();
      return;
    }
    int padding = (TERMINAL_WIDTH - text.length()) / 2;
    if (padding > 0) {
      System.out.println(" ".repeat(padding) + text);
    } else {
      System.out.println(text);
    }
  }

  public static void printCenterPrompt(String text) {
    if (text == null) text = "";
    int padding = (TERMINAL_WIDTH - text.length()) / 2;
    if (padding > 0) {
      System.out.print(" ".repeat(padding) + text);
    } else {
      System.out.print(text);
    }
  }

  public static void printMenu(String title, String[] options) {
    System.out.println();
    if (title != null && !title.isEmpty()) {
      printCenter(title);
    }

    // Find max length of options to center the block
    int maxLength = 0;
    for (String option : options) {
      if (option.length() > maxLength) {
        maxLength = option.length();
      }
    }

    int blockPadding = (TERMINAL_WIDTH - maxLength) / 2;
    String padStr = " ".repeat(Math.max(0, blockPadding));

    for (String option : options) {
      System.out.println(padStr + option);
    }
  }
}
