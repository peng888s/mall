package com.bdqn.mall.service;


import com.bdqn.mall.entity.Admin;
import com.bdqn.mall.util.PageUtil;

import java.util.List;

public interface AdminService {
    boolean add(Admin admin);
    boolean update(Admin admin);

    List<Admin> getList(String adminName, PageUtil pageUtil);
    Admin get(String adminName, Integer adminId);
    Admin login(String adminName, String adminPassword);
    Integer getTotal(String adminName);
}
