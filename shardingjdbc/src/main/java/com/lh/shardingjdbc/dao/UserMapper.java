package com.lh.shardingjdbc.dao;

import com.lh.shardingjdbc.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insert(User record);

    User selectByPrimaryKey(int id);
}
