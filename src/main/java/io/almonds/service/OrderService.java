package io.almonds.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.almonds.entity.Order;

public class OrderService {

  private Map<Integer, Order> orders = new HashMap<>();

  public Order createAnOrder(String item, String sugarLevel, String iceLevel, BigDecimal price) {
    Order order = new Order(item, sugarLevel, iceLevel, price);
    orders.put(order.getOrderNo(), order);
    return order;
  }

  public Collection<Order> getOrders() {
    return orders.values();
  }

  public Order getOrderByNo(int orderNo) {
    return orders.get(orderNo);
  }

  public Order updateOrder(int orderNo, Order order) {
    orders.put(orderNo, order);
    return order;
  }

  public Order deleteOrder(int orderNo) {
    return orders.remove(orderNo);
  }
}
