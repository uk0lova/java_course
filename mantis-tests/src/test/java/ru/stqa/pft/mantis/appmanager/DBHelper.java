package ru.stqa.pft.mantis.appmanager;

import ru.stqa.pft.mantis.tests.ChangePasswordTests;

import java.sql.*;

public class DBHelper {
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    public Connection getConn() {
        return conn;
    }

    public Statement getSt() {
        return st;
    }

    public ResultSet getRs() {
        return rs;
    }

    public DBHelper invoke() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=&serverTimezone=UTC");
        st = conn.createStatement();
        rs = st.executeQuery
                ("select username, email from mantis_user_table where username != 'administrator'");
        return this;
    }
}
