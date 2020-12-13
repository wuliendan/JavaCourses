package com.lh.shardingxa.example;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ExampleMain {

    public static void main(String[] args) throws IOException, SQLException {
        File file = ResourceUtils.getFile("classpath:application.yaml");
        XAOrderService xaOrderService = new XAOrderService(file);
        xaOrderService.init();
        xaOrderService.insertFirst();
        xaOrderService.insertSecond();
    }
}
