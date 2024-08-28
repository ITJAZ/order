package io.almonds.service;

import java.math.BigDecimal;
import java.util.Collection;

import io.almonds.entity.Order;

class DbOrderService implements OrderService {

  @Override
  public Order createAnOrder(String item, String sugarLevel, String iceLevel, BigDecimal price) {
    throw new UnsupportedOperationException("Unimplemented method 'createAnOrder'");
  }

  @Override
  public Collection<Order> getOrders() {
    throw new UnsupportedOperationException("Unimplemented method 'getOrders'");
  }

  @Override
  public Order getOrderByNo(int orderNo) {
    throw new UnsupportedOperationException("Unimplemented method 'getOrderByNo'");
  }

  @Override
  public Order updateOrder(int orderNo, Order order) {
    throw new UnsupportedOperationException("Unimplemented method 'updateOrder'");
  }

  @Override
  public Order deleteOrder(int orderNo) {
    throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
  }

}
