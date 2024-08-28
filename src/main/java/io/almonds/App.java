package io.almonds;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            System.out.println("Please specify an action: [create(c)/read(r)/update(u)/delete(d)]");
            while (!"exit".equals(input = scanner.nextLine())) {
                System.out.println(input);
            }
        }
    }

}