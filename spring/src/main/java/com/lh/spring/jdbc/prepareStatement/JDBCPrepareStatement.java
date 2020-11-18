package com.lh.spring.jdbc.prepareStatement;

import com.lh.spring.jdbc.JDBCDemo;
import com.lh.spring.jdbc.JDBCUtils;

import java.sql.*;

public class JDBCPrepareStatement {

    private static String sqlStatement;

    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            sqlStatement = "SELECT * FROM User WHERE name = ?";
            statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, "JAVA");
            resultSet = statement.executeQuery();
        } catch (Exception e) {
            throw e;
        } finally {
            JDBCUtils.closeResource(connection, statement, resultSet);
        }
    }
}
