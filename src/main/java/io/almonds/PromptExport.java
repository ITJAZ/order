package io.almonds;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import io.almonds.service.OrderService;
import io.almonds.util.Exporter;

public class PromptExport {

  private Scanner scanner;

  public PromptExport(Scanner scanner) {
    this.scanner = scanner;
  }

  public void export(OrderService orderService) {
    Exporter exporter = null;
    while (exporter == null) {
      System.out.println("Please select export mode: (1 for csv, 2 for xlsx)");
      String input = scanner.nextLine();
      try {
        int mode = Integer.parseInt(input);
        exporter = Exporter.get(mode);
      } catch (Exception e) {
        System.err.println("Invalid input: " + input);
        continue;
      }
    }

    File file = new File("output.csv");
    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
      exporter.export(fileOutputStream, orderService.getOrders());
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Exported to: " + file.getAbsolutePath());
  }
}
