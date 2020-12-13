package com.lh.shardingxa.example;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class XAOrderService {

    private final DataSource dataSource;
    
    public XAOrderService(final File yamlFile) throws IOException, SQLException {
        dataSource = YamlShardingSphereDataSourceFactory.createDataSource(yamlFile);
    }

    public void init() throws SQLException {
        try (Connection connection = dataSource.getConnection()){
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM t_order");
            connection.commit();
        }
    }

    public void insertFirst() throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT t_order(order_id, user_id) VALUES (?, ?)");

            System.out.println("First XA Insert...");
            for (int i = 0; i < 10; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setInt(2, i);
                preparedStatement.executeUpdate();
            }
            connection.commit();
            System.out.println("First XA Insert Success");
        }
    }

    public void insertSecond() throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT t_order(order_id, user_id) VALUES (?, ?)");

            System.out.println("Second XA Insert...");
            for (int i = 0; i < 10; i++) {
                preparedStatement.setInt(1, i + 5);
                preparedStatement.setInt(2, i + 5);
                preparedStatement.executeUpdate();
            }
            connection.commit();
            System.out.println("Second XA Insert Success");
        }
    }
}
