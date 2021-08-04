package com.librarymanagementsystem;

import java.sql.*;

public class DbCon {
    Connection con;
    Statement statement;

    public DbCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_Management_System", "root", "");
            statement = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
