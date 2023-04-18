package com.bdqn.mall.service.impl;

import com.bdqn.mall.dao.UserMapper;
import com.bdqn.mall.entity.User;
import com.bdqn.mall.service.UserService;
import com.bdqn.mall.util.OrderUtil;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean add(User user) {
        return userMapper.insertOne(user)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean update(User user) {
        return userMapper.updateOne(user)>0;
    }

    public List<User> getList(User user, OrderUtil orderUtil, PageUtil pageUtil) {
        return userMapper.select(user,orderUtil,pageUtil);
    }

    @Override
    public User get(Integer userId) {
        return userMapper.selectOne(userId);
    }

    @Override
    public User login(String userName, String userPassword) {
        return userMapper.selectByLogin(userName,userPassword);
    }

    @Override
    public Integer getTotal(User user) {
        return userMapper.selectTotal(user);
    }
}
