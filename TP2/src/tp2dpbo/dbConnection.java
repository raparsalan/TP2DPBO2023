/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp2dpbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author VAIO
 */
public class dbConnection {
    private Statement stmt = null;
    private Connection conn = null;

    public dbConnection() {
        String ConAddress = "jdbc:mysql://localhost/tp2dpbo";
        String user = "root";
        String pass = "";
        connect(ConAddress, user, pass);
    }

    private void connect(String ConnAddress, String username, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(ConnAddress, username, pass);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ResultSet selectQuery(String sql) {
        try {
            stmt.executeQuery(sql);
            return stmt.getResultSet();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public int updateQuery(String sql) {
        try {
            return stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public Statement getStatement() {
        return stmt;
    }
}
