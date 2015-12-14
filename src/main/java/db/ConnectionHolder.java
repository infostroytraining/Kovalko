package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHolder {
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql:mydb", "postgres", "root");
    }

    public static void setConnection(Connection connection) throws SQLException {
        ConnectionHolder.connection = connection;
    }
}