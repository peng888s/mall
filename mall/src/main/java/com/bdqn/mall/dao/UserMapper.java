package com.bdqn.mall.dao;

import com.bdqn.mall.entity.User;
import com.bdqn.mall.util.OrderUtil;
import com.bdqn.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper {
    Integer insertOne(@Param("user") User user);
    Integer updateOne(@Param("user") User user);

    List<User> select(@Param("user") User user, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);
    User selectOne(@Param("userId") Integer userId);
    User selectByLogin(@Param("userName") String userName, @Param("userPassword") String userPassword);
    Integer selectTotal(@Param("user") User user);
}
