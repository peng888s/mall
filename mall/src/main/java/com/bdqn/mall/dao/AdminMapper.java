package com.bdqn.mall.dao;

import com.bdqn.mall.entity.Admin;
import com.bdqn.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    Integer insertOne(@Param("admin") Admin admin);
    Integer updateOne(@Param("admin") Admin admin);

    List<Admin> select(@Param("adminName") String adminName, @Param("pageUtil") PageUtil pageUtil);
    Admin selectOne(@Param("adminName") String adminName, @Param("adminId") Integer adminId);
    Admin selectByLogin(@Param("adminName") String adminName, @Param("adminPassword") String adminPassword);
    Integer selectTotal(@Param("adminName") String adminName);
}