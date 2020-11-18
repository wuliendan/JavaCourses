package com.lh.spring.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

    private static String sqlStatement;

    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = null;

        sqlStatement = "SELECT * FROM User";
        query(statement, sqlStatement, resultSet);

        sqlStatement = "INSERT INTO User(name, age) VALUES ('JAVA', 20)";
        insert(statement, sqlStatement);

        sqlStatement = "UPDATE User SET age = 30 WHERE name='JAVA'";
        update(statement, sqlStatement);

        sqlStatement = "DELETE FROM User WHERE name='JAVA'";
        delete(statement, sqlStatement);

        JDBCUtils.closeResource(connection, statement, resultSet);
    }

    public static void query(Statement statement, String sql, ResultSet resultSet) throws SQLException {
        resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
        }
    }

    public static int insert(Statement statement, String sql) throws SQLException {
        return statement.executeUpdate(sql);
    }

    public static int update(Statement statement, String sql) throws SQLException {
        return statement.executeUpdate(sql);
    }

    public static int delete(Statement statement, String sql) throws SQLException {
        return statement.executeUpdate(sql);
    }
}
