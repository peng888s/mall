package com.bdqn.mall.dao;

import com.bdqn.mall.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface AddressMapper {
    Integer insertOne(@Param("address") Address address);
    Integer updateOne(@Param("address") Address address);

    List<Address> select(@Param("addressName") String addressName, @Param("addressRegionId") String addressRegionId);
    Address selectOne(@Param("addressAreaId") String addressAreaId);
    List<Address> selectRoot();
}