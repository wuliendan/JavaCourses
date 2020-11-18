package com.lh.spring.aop;

import org.springframework.stereotype.Component;

@Component("landlord")
public class Landlord {

    public void service() {
        System.out.println("签合同");
    }
}
