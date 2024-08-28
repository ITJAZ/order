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
    System.out.println("Creating Mode... ");
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
    System.out.println("Reading mode... ");
    System.out.println("Please enter an order no or * for all: ");
    String orderNoString = scanner.nextLine();

    int tried = 0;
    int orderNo = -1;

    while (tried++ < 3) {
      if ("*".equals(orderNoString)) {
        System.out.println("Listing all orders");
        System.out.println(orderService.getOrders());
        return;
      }

      try {
        orderNo = Integer.parseInt(orderNoString);
        break;
      } catch (Exception e) {
        System.err.printf("Invalid order no input: %s, only accept number... \n", orderNoString);
        System.out.println("Please enter the order no: ");
        orderNoString = scanner.nextLine();
        continue;
      }
    }
    Order order = orderService.getOrderByNo(orderNo);
    if (order == null) {
      System.err.println("Order not found: " + orderNo);
      return;
    }
    System.out.println("Order info with no: " + orderNo);
    System.out.println(order);
  }

  public void update() {
    System.out.println("Updating Mode... ");
    Order order = null;

    while (order == null) {
      System.out.println("Please enter the [Order No] of the order you want to update: ");
      String orderNoString = scanner.nextLine();
      try {
        int orderNo = Integer.parseInt(orderNoString);
        order = orderService.getOrderByNo(orderNo);
      } catch (Exception e) {
        System.err.printf("Invalid order no input: %s, only accept number... \n", orderNoString);
        System.out.println("Please enter the order no: ");
        orderNoString = scanner.nextLine();
      }
    }

    System.out.println("Please enter your item name: ");
    String item = scanner.nextLine();
    System.out.println("Please enter your sugar level: ");
    String sugarLevel = scanner.nextLine();
    System.out.println("Please enter your ice level: ");
    String iceLevel = scanner.nextLine();
    System.out.println("Please enter the price: ");
    String priceString = scanner.nextLine();

    BigDecimal price;
    while (true) {
      try {
        price = new BigDecimal(priceString);
        break;
      } catch (Exception e) {
        System.err.printf("Invalid price input: %s, only accept number... \n", priceString);
        System.out.println("Please enter the price: ");
        priceString = scanner.nextLine();
        continue;
      }
    }

    order.setItem(item);
    order.setIceLevel(iceLevel);
    order.setSugarLevel(sugarLevel);
    order.setPrice(price);

    orderService.updateOrder(order.getOrderNo(), order);

    System.out.println("Order Updated.");
    System.out.println(order);
    System.out.println("Current Orders: ");
    System.out.println(orderService.getOrders());
  }

  public void delete() {
    System.out.println("Deleting Mode... ");
    int orderNo;
    while (true) {
      System.out.println("Please enter the [Order No] of the order you want to delete: ");
      String orderNoString = scanner.nextLine();
      try {
        orderNo = Integer.parseInt(orderNoString);
        break;
      } catch (Exception e) {
        System.err.printf("Invalid order no input: %s, only accept number... \n", orderNoString);
      }
    }
    orderService.deleteOrder(orderNo);
    System.out.println("Order deleted: " + orderNo);
    System.out.println("Current Orders: ");
    System.out.println(orderService.getOrders());
  }
}
