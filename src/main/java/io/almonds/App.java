package io.almonds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.almonds.db.ConnectionManager;

public class App {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString(1));
                    }
                }
            }
        }
    }
}