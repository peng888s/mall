package com.bdqn.mall.dao;

import com.bdqn.mall.entity.Property;
import com.bdqn.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface PropertyMapper {
    Integer insertOne(@Param("property") Property property);

    Integer insertList(@Param("propertyList") List<Property> propertyList);
    Integer updateOne(@Param("property") Property property);
    Integer deleteList(@Param("propertyIdList") Integer[] propertyIdList);

    List<Property> select(@Param("property") Property property, @Param("pageUtil") PageUtil pageUtil);
    Property selectOne(@Param("propertyId") Integer propertyId);
    Integer selectTotal(@Param("property") Property property);
}
