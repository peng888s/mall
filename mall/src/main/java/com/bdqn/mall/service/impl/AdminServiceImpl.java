package com.bdqn.mall.service.impl;

import com.bdqn.mall.dao.AdminMapper;
import com.bdqn.mall.entity.Admin;
import com.bdqn.mall.service.AdminService;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(Admin admin) {
        return adminMapper.insertOne(admin)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(Admin admin) {
        return adminMapper.updateOne(admin)>0;
    }

    @Override
    public List<Admin> getList(String adminName, PageUtil pageUtil) {
        return adminMapper.select(adminName,pageUtil);
    }

    @Override
    public Admin get(String adminName,Integer adminId) {
        return adminMapper.selectOne(adminName,adminId);
    }

    @Override
    public Admin login(String adminName, String adminPassword) {
        return adminMapper.selectByLogin(adminName,adminPassword);
    }

    @Override
    public Integer getTotal(String adminName) {
        return adminMapper.selectTotal(adminName);
    }
}
