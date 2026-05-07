package util;

import java.io.Console;
import java.util.Scanner;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class ConsoleInput {
  public static final Scanner scanner = new Scanner(System.in);

  public ConsoleInput() {}

  public static Scanner getScanner() {
    return scanner;
  }

  public static String readPassword(String prompt) {
    try {
      Terminal terminal = TerminalBuilder.builder().system(true).dumb(false).build();
      terminal.enterRawMode();
      StringBuilder password = new StringBuilder();
      System.out.print(prompt);

      int ch;
      while ((ch = terminal.reader().read()) != -1) {
        if (ch == '\r' || ch == '\n') {
          System.out.println();
          break;
        } else if (ch == 127 || ch == '\b') {
          if (password.length() > 0) {
            password.deleteCharAt(password.length() - 1);
            System.out.print("\b \b");
          }
        } else if (ch >= 32) {
          password.append((char) ch);
          System.out.print("*");
        }
      }
      terminal.close();
      return password.toString();

    } catch (Exception e) {
      // IDE fallback – uses Console or Scanner (no masking)
      Console console = System.console();
      if (console != null) {
        return new String(console.readPassword(prompt));
      } else {
        System.out.print(prompt + " (no masking in IDE): ");
        return scanner.nextLine();
      }
    }
  }
}
