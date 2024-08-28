package io.almonds;

import java.math.BigDecimal;
import java.util.Scanner;

import io.almonds.entity.Order;
import io.almonds.service.OrderService;

public class PromptOrder {

  private Scanner scanner;

  private OrderService orderService;

  public PromptOrder(Scanner scanner) {
    this.scanner = scanner;
    this.orderService = new OrderService();
  }

  public void create() {
    System.out.println("Please enter your item name: ");
    String item = scanner.nextLine();
    System.out.println("Please enter your sugar level: ");
    String sugarLevel = scanner.nextLine();
    System.out.println("Please enter your ice level: ");
    String iceLevel = scanner.nextLine();
    System.out.println("Please enter the price: ");
    String priceString = scanner.nextLine();
    int tried = 0;
    BigDecimal price = null;
    while (tried++ < 3) {
      try {
        price = new BigDecimal(priceString);
      } catch (Exception e) {
        System.err.printf("Invalid price input: %s, only accept number... \n", priceString);
        System.out.println("Please enter the price: ");
        priceString = scanner.nextLine();
        continue;
      }
    }

    if (price == null) {
      System.err.println("Too many invalid tried, aborting...");
      return;
    }

    Order order = orderService.createAnOrder(item, sugarLevel, iceLevel, price);

    System.out.println("Order Created.");
    System.out.println(order);
    System.out.println("Current Orders: ");
    System.out.println(orderService.getOrders());
    return;
  }

  public void read() {

  }

  public void update() {

  }

  public void delete() {

  }
}
