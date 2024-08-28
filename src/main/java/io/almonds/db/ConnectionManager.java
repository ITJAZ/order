package io.almonds.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

  private static Connection connection;

  public static Connection getConnection() throws SQLException {
    if (connection == null) {
      String url = "jdbc:h2:mem:mydb";
      String id = System.getenv("DB_ID");
      String password = System.getenv("DB_SECRET");
      connection = DriverManager.getConnection(url, id, password);
    }
    return connection;
  }

  public static void close() throws SQLException {
    connection.close();
  }
}
