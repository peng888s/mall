package com.bdqn.mall.dao;

import com.bdqn.mall.entity.PropertyValue;
import com.bdqn.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface PropertyValueMapper {
    Integer insertOne(@Param("propertyValue") PropertyValue propertyValue);

    Integer insertList(@Param("propertyValueList") List<PropertyValue> propertyValueList);
    Integer updateOne(@Param("propertyValue") PropertyValue propertyValue);
    Integer deleteList(@Param("propertyValueIdList") Integer[] propertyValueIdList);

    List<PropertyValue> select(@Param("propertyValue") PropertyValue propertyValue, @Param("pageUtil") PageUtil pageUtil);
    PropertyValue selectOne(@Param("propertyValueId") Integer propertyValueId);
    Integer selectTotal(@Param("propertyValue") PropertyValue propertyValue);
}
