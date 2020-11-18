package com.lh.spring.jdbc.batch;

import com.lh.spring.jdbc.JDBCUtils;

import java.sql.*;

public class JDBCBatch {

    // Statement
//    public static void main(String[] args) {
//        String sql1 = "INSERT INTO User(id,name) VALUES (1,'aaa')";
//        String sql2 = "INSERT INTO User(id,name) VALUES (2,'BBB')";
//        String sql3 = "UPDATE User SET name='CCC' WHERE id=1";
//        String sql4 = "INSERT INTO User(id,name) VALUES (3,'DDD')";
//        String sql5 = "DELETE FROM User WHERE id=2";
//
//        Connection connection = JDBCUtils.getConnection();
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            statement = connection.createStatement();
//            statement.addBatch(sql1);
//            statement.addBatch(sql2);
//            statement.addBatch(sql3);
//            statement.addBatch(sql4);
//            statement.addBatch(sql5);
//            statement.executeBatch();
//            statement.clearBatch();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            JDBCUtils.closeResource(connection, statement, resultSet);
//        }
//    }

    //PrepareStatement
    public static void main(String[] args) {
        String sql = "INSERT INTO User(id, name) VALUES (?, ?)";

        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < 100; i++) {
                statement.setInt(1, i);
                statement.setString(2, "Name_" + i);
                statement.addBatch();
            }
            statement.executeBatch();
            statement.clearBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, statement, resultSet);
        }
    }
}
