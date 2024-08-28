package io.almonds;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    private static final String COMMAND_HINT = "Please specify an action: [create(c)/read(r)/update(u)/delete(d)/export(e)/exit]";

    public static void main(String[] args) throws SQLException {
        try (Scanner scanner = new Scanner(System.in)) {
            PromptOrder promptOrder = new PromptOrder(scanner);
            String input;
            System.out.println(COMMAND_HINT);
            while (!"exit".equals(input = scanner.nextLine())) {
                if (input == null)
                    continue;

                switch (input.toLowerCase()) {
                    case "create":
                    case "c":
                        promptOrder.create();
                        System.out.println(COMMAND_HINT);
                        break;
                    case "read":
                    case "r":
                        promptOrder.read();
                        System.out.println(COMMAND_HINT);
                        break;
                    case "update":
                    case "u":
                        promptOrder.update();
                        System.out.println(COMMAND_HINT);
                        break;
                    case "delete":
                    case "d":
                        promptOrder.delete();
                        System.out.println(COMMAND_HINT);
                        break;
                    default:
                        System.out.printf("Unknown command: %s \n", input);
                        System.out.println(COMMAND_HINT);
                        continue;
                }
            }
        }
    }

}