package com.chipndale.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
  public static Connection getConnection() throws SQLException {
    // jdbc:oracle:thin:@hostname:port Number:databaseName
    String url = System.getenv("AWS_DEMO_1909_DB_URL");
    String username = System.getenv("BANK_DB_USERNAME");
    String password = System.getenv("BANK_DB_PASSWORD");
    return DriverManager.getConnection(url, username, password);
  }
}
