package com.moviestorage.moviestorage;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class dbConn {

    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    public static final String URL = "";
    public static final String USER = "";
    public static final String PASSWORD = "";

    @Test
    public void testDBConn() throws Exception {
        Class.forName(DRIVER);
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("connection result: " + conn);

            Statement stmt = conn.createStatement();

            String sql = "select * from moviestorage.users";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                System.out.println("name: " + rs.getString("name"));
            }


        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
