package com.lh.spring.bean.component;

import org.springframework.stereotype.Component;

@Component("userService")
public class UserService {

    public void addUser() {
        System.out.println("UserService... ...");
    }
}
