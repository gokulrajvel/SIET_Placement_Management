package com.gokulrajvel.placementportal.data.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class PlacementJDBCConnection {
  private static final String URL = "jdbc:mysql://localhost:3306/placementDB";
  private static final String USER = "root";
  private static final String PASSWORD = "mrdio2";

  public static Connection getConnection() {
    try {
      return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (java.sql.SQLException e) {
      System.out.println("Connection Failed: " + e.getMessage());
      return null;
    }
  }
}
