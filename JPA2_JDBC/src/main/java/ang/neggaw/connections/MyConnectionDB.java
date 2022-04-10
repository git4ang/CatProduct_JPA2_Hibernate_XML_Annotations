package ang.neggaw.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * author by: ANG
 * since: 10/04/2022 10:26
 */

public class MyConnectionDB {

    private static Connection cn = null;

    private MyConnectionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/catProducts_db";
            String user = "root";
            String password = "";
            cn = DriverManager.getConnection(url, user, password);
            cn.setAutoCommit(true);

            System.out.println("Connection created successfully with the la DB: " + cn.getCatalog());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error connection: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        if(cn == null || cn.isClosed()) new MyConnectionDB();
        return cn;
    }
}