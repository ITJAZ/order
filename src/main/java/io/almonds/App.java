package io.almonds;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        try (Scanner scanner = new Scanner(System.in)) {
            PromptOrder promptOrder = new PromptOrder(scanner);
            String input;
            System.out.println("Please specify an action: [create(c)/read(r)/update(u)/delete(d)]");
            while (!"exit".equals(input = scanner.nextLine())) {
                if (input == null)
                    continue;

                switch (input.toLowerCase()) {
                    case "create":
                    case "c":
                        promptOrder.create();
                        break;
                    case "read":
                    case "r":
                        promptOrder.read();
                        break;
                    case "update":
                    case "u":
                        promptOrder.update();
                        break;
                    case "delete":
                    case "d":
                        promptOrder.delete();
                        break;
                }
            }
        }
    }

}