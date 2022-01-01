/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.ihu.ermistv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.ClassLoader;

/**
 *
 * @author User
 */
public class DBConnection {
    public static Connection c;

    public static void connect(String url, String user, String pass) throws SQLException {
        DriverManager.setLoginTimeout(1000);
        c = DriverManager.getConnection(url, user, pass);
    }

}
