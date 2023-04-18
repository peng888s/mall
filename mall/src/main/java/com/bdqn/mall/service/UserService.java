package com.bdqn.mall.service;

import com.bdqn.mall.entity.User;
import com.bdqn.mall.util.OrderUtil;
import com.bdqn.mall.util.PageUtil;

import java.util.List;

public interface UserService {
    boolean add(User user);
    boolean update(User user);

    List<User> getList(User user, OrderUtil orderUtil, PageUtil pageUtil);
    User get(Integer userId);
    User login(String userName, String userPassword);
    Integer getTotal(User user);
}
