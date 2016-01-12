package com.zeerow.qa.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yoosuf on 7/20/2015.
 */
public class DBUtil {
    private static final String DEFAULT_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DEFAULT_URL = "jdbc:mysql://52.22.69.118:3306/test_coinbates";
    private static final String DEFAULT_USERNAME = "appusrcoinbates";
    private static final String DEFAULT_PASSWORD = "Admin#99005";
/*
    private static final String DEFAULT_DRIVER = "org.postgresql.Driver";
    private static final String DEFAULT_URL = "jdbc:postgresql://localhost:5432/party";
    private static final String DEFAULT_USERNAME = "pgsuper";
    private static final String DEFAULT_PASSWORD = "pgsuper";
*/
/*
    private static final String DEFAULT_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/party";
    private static final String DEFAULT_USERNAME = "party";
    private static final String DEFAULT_PASSWORD = "party";
*/


    public static void main(String [] args){
        deleteUserAccount("mak83826@gmail.com");
        deleteUserAccount("coinbatestest@gmail.com");

    }





    public static Connection createConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        if ((username == null) || (password == null) || (username.trim().length() == 0) || (password.trim().length() == 0)) {
            return DriverManager.getConnection(url);
        } else {
            return DriverManager.getConnection(url, username, password);
        }
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, Object>> map(ResultSet rs) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        try {
            if (rs != null) {
                ResultSetMetaData meta = rs.getMetaData();
                int numColumns = meta.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<String, Object>();
                    for (int i = 1; i <= numColumns; ++i) {
                        String name = meta.getColumnName(i);
                        Object value = rs.getObject(i);
                        row.put(name, value);
                    }
                    results.add(row);
                }
            }
        } finally {
            close(rs);
        }
        return results;
    }

    public static List<Map<String, Object>> query(Connection connection, String sql, List<Object> parameters) throws SQLException {
        List<Map<String, Object>> results = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);

            int i = 0;
            for (Object parameter : parameters) {
                ps.setObject(++i, parameter);
            }
            rs = ps.executeQuery();
            results = map(rs);
        } finally {
            close(rs);
            close(ps);
        }
        return results;
    }

    public static int update(Connection connection, String sql, List<Object> parameters) throws SQLException {
        int numRowsUpdated = 0;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);

            int i = 0;
            for (Object parameter : parameters) {
                ps.setObject(++i, parameter);
            }
            numRowsUpdated = ps.executeUpdate();
        } finally {
            close(ps);
        }
        return numRowsUpdated;
    }

    public static void deleteUserAccount(String email)  {
        long begTime = System.currentTimeMillis();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;

        String sql =  "delete from users where email = '"+email+"'";
        try {
            connection = createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            close(rs);
            close(ps);
            close(connection);

        }
    }

    public static void getUserDetails(String email)  {
        long begTime = System.currentTimeMillis();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;

        String sql =  "SELECT social_id FROM coinbates.users where email = '"+email+"'";
        try {
            connection = createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            close(rs);
            close(ps);
            close(connection);

        }
    }
}
