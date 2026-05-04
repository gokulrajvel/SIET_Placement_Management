package com.gokulrajvel.placementportal.data.repository;

public class PlacementJDBCConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/placementDB";
    private static final String USER = "root";
    private static final String PASSWORD = "mrdio2";

    public static java.sql.Connection getConnection() {
        try {
            return java.sql.DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (java.sql.SQLException e) {
            System.out.println("Connection Failed: " + e.getMessage());
            return null;
        }
    }
}
