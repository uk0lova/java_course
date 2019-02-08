package ru.stqa.pft.mantis.appmanager;

import java.sql.*;

public class DBHelper {
    private ApplicationManager app;

    public DBHelper(ApplicationManager app) {
        this.app = app;
    }

    public String[] getUserData() {
        String[] result = new String[2];
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery
                    ("select username, email from mantis_user_table where username != 'administrator'");
            rs.first();
            result[0] = rs.getString("username");
            result[1] = rs.getString("email");
            System.out.println(rs.getString("username"));

            rs.close();
            st.close();
            conn.close();
            return result;

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }
}
