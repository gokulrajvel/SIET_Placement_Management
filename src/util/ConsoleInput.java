package util;

import java.util.Scanner;

public class ConsoleInput {
    public static final Scanner scanner = new Scanner(System.in);
    public ConsoleInput() {}
    public static Scanner getScanner() {
        return scanner;
    }
}
