/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.ihu.ermistv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author User
 */
public class DBConnection {
    public static Connection c;
    
    

    public static void connect() throws SQLException {
        CrunchifyGetPropertyValues properties = new CrunchifyGetPropertyValues("app/config.properties");
        String userDB = properties.getProperty("user");
        String passDB = properties.getProperty("pass");
        String url = properties.getProperty("url");
        DriverManager.setLoginTimeout(1000);
        c = DriverManager.getConnection(url, userDB, passDB);
    }
    
    
}


