package io.almonds.service;

import java.math.BigDecimal;
import java.util.Collection;

import io.almonds.entity.Order;

public interface OrderService {

  static OrderService get(int mode) {
    if (mode == 1)
      return new MemoryOrderService();

    return null;
  }

  public Order createAnOrder(String item, String sugarLevel, String iceLevel, BigDecimal price);

  public Collection<Order> getOrders();

  public Order getOrderByNo(int orderNo);

  public Order updateOrder(int orderNo, Order order);

  public Order deleteOrder(int orderNo);
}
