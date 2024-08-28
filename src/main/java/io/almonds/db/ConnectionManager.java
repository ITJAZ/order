package io.almonds.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

  public static Connection getConnection() throws SQLException {
    String url = "jdbc:h2:mem:mydb;DB_CLOSE_DELAY=-1";
    String id = System.getenv("DB_ID");
    String password = System.getenv("DB_SECRET");
    return DriverManager.getConnection(url, id, password);
  }

}
