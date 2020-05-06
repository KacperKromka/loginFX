package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {


    public static Connection conDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection myConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/login_sql?serverTimezone=UTC",
                    "root",
                    "s3cret"
            );
            return myConnection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
