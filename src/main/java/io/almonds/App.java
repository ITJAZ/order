package io.almonds;

import java.util.Scanner;

import io.almonds.service.OrderService;

public class App {
    private static final String COMMAND_HINT = "Please specify an action: [create(c)/read(r)/update(u)/delete(d)/export(e)/exit]";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            OrderService orderService = null;
            while (orderService == null) {
                System.out.println("Please choose service mode: 1 for in-memory, 2 for database");
                String serviceMode = scanner.nextLine();
                try {
                    int mode = Integer.parseInt(serviceMode);
                    orderService = OrderService.get(mode);
                } catch (Exception e) {
                    System.err.println("Unknown Service mode: " + serviceMode);
                    continue;
                }
            }

            PromptOrder promptOrder = new PromptOrder(orderService, scanner);
            PromptExport export = new PromptExport(scanner);

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
                    case "export":
                    case "e":
                        export.export(orderService);
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