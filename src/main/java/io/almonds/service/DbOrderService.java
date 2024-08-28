package io.almonds.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.almonds.db.ConnectionManager;
import io.almonds.entity.Order;

class DbOrderService implements OrderService {

  public DbOrderService() {
    try (Connection connection = ConnectionManager.getConnection()) {
      int tableCount = 0;
      String selectTableSql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'ORDERS'";
      try (PreparedStatement preparedStatement = connection.prepareStatement(selectTableSql);
          ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          tableCount = resultSet.getInt(1);
        }
      }

      if (tableCount != 0)
        return;

      String createTableScript = "CREATE TABLE IF NOT EXISTS ORDERS (ORDER_NO INT, ITEM VARCHAR(10), SUGAR_LEVEL VARCHAR(10), ICE_LEVEL VARCHAR(10), PRICE DOUBLE)";
      try (PreparedStatement preparedStatement = connection.prepareStatement(createTableScript)) {
        preparedStatement.execute();
      }
      String selectTableSql2 = "SELECT * FROM INFORMATION_SCHEMA.TABLES ";
      try (PreparedStatement preparedStatement = connection.prepareStatement(selectTableSql2);
          ResultSet resultSet = preparedStatement.executeQuery()) {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
          for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            System.out.println(columnName + ":" + resultSet.getString(i));
          }
        }
      }

      System.out.println("table created");
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public Order createAnOrder(String item, String sugarLevel, String iceLevel, BigDecimal price) {
    try (Connection connection = ConnectionManager.getConnection()) {
      Order order = new Order(item, sugarLevel, iceLevel, price);
      StringBuffer sql = new StringBuffer();

      sql.append(" INSERT INTO ORDERS VALUES (");
      sql.append(" ?,"); // order no
      sql.append(" ?,"); // item
      sql.append(" ?,"); // sugar level
      sql.append(" ?,"); // ice level
      sql.append(" ?"); // price
      sql.append(" )");

      try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
        int index = 1;
        preparedStatement.setInt(index++, order.getOrderNo());
        preparedStatement.setString(index++, order.getItem());
        preparedStatement.setString(index++, order.getSugarLevel());
        preparedStatement.setString(index++, order.getIceLevel());
        preparedStatement.setBigDecimal(index++, order.getPrice());

        preparedStatement.executeUpdate();
      }

      return order;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  @Override
  public Collection<Order> getOrders() {
    try (Connection connection = ConnectionManager.getConnection()) {
      List<Order> orders = new ArrayList<>();
      String sql = "SELECT * FROM ORDERS";
      try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
          ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          int index = 1;

          int orderNo = resultSet.getInt(index++);
          String item = resultSet.getString(index++);
          String sugarLevel = resultSet.getString(index++);
          String iceLevel = resultSet.getString(index++);
          BigDecimal price = resultSet.getBigDecimal(index++);

          Order order = new Order(orderNo, item, sugarLevel, iceLevel, price);

          orders.add(order);
        }
      }

      return orders;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return new ArrayList<>();
    }
  }

  @Override
  public Order getOrderByNo(int orderNo) {
    try (Connection connection = ConnectionManager.getConnection()) {

      String sql = "SELECT * FROM ORDERS WHERE ORDER_NO = ?";

      try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setInt(1, orderNo);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
          while (resultSet.next()) {
            int index = 1;

            String item = resultSet.getString(index++);
            String sugarLevel = resultSet.getString(index++);
            String iceLevel = resultSet.getString(index++);
            BigDecimal price = resultSet.getBigDecimal(index++);

            Order order = new Order(orderNo, item, sugarLevel, iceLevel, price);

            return order;
          }
        }
        return null;
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  @Override
  public Order updateOrder(int orderNo, Order order) {
    Order where = getOrderByNo(orderNo);
    if (where == null) {
      System.out.println("Order not found: " + orderNo);
      return null;
    }
    try (Connection connection = ConnectionManager.getConnection()) {
      StringBuffer sql = new StringBuffer();
      sql.append("UPDATE ORDERS SET ");
      sql.append("       ITEM = ?,");
      sql.append("       SUGAR_LEVEL = ?,");
      sql.append("       ICE_LEVEL = ?,");
      sql.append("       PRICE = ?");
      sql.append(" WHERE ORDER_NO = ? ");

      try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
        int index = 1;
        preparedStatement.setString(index++, order.getItem());
        preparedStatement.setString(index++, order.getSugarLevel());
        preparedStatement.setString(index++, order.getIceLevel());
        preparedStatement.setBigDecimal(index++, order.getPrice());
        preparedStatement.setInt(1, orderNo);
        preparedStatement.executeUpdate();

        return order;
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  @Override
  public Order deleteOrder(int orderNo) {
    Order order = getOrderByNo(orderNo);
    if (order == null) {
      System.out.println("Order not found: " + orderNo);
      return null;
    }

    try (Connection connection = ConnectionManager.getConnection()) {
      StringBuffer sql = new StringBuffer();
      sql.append("DELETE ORDERS ");
      sql.append(" WHERE ORDER_NO = ? ");

      try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
        preparedStatement.setInt(1, orderNo);

        preparedStatement.executeUpdate();
        return order;
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

}
