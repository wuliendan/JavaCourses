package com.lh.spring.jdbc.transaction;

import com.lh.spring.jdbc.JDBCDemo;
import com.lh.spring.jdbc.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTransaction {

    private static String sqlStatement;

    public static void main(String[] args) throws SQLException {
        JDBCDemo jdbcDemo = new JDBCDemo();

        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = null;

        sqlStatement = "SELECT * FROM User";
        jdbcDemo.query(statement, sqlStatement, resultSet);

        try {
            connection.setAutoCommit(false);
            sqlStatement = "INSERT INTO User(name, age) VALUES ('JAVA', 20)";
            jdbcDemo.insert(statement, sqlStatement);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            JDBCUtils.closeResource(connection, statement, resultSet);
        }
    }
}
