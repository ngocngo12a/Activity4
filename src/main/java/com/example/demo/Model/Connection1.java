package com.example.demo.Model;

import java.sql.Connection;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class Connection1 {
    private Connection connection;

    public Connection1() {
    }

    public static Connection1 getInstance() {
        return ConnectionHelper.demoConnection;
    }

    private static class ConnectionHelper {
        private static final Connection1 demoConnection = new Connection1();
    }
    public Connection1(Connection connection) {
        this.connection = connection;
    }
    public Connection getConnections() {
        try {
            connection = getConnection("jdbc:mysql://127.0.0.1:3306/user", "root", "ngochoatuyen");
            return connection;
        } catch (Exception e) {
            System.out.println("Loi khi connect database" + e);
        }
        return null;
    }

}
