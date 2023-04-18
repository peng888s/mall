package com.bdqn.mall.dao;

import com.bdqn.mall.entity.Category;
import com.bdqn.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CategoryMapper {
    Integer insertOne(@Param("category") Category category);
    Integer updateOne(@Param("category") Category category);

    List<Category> select(@Param("categoryName") String categoryName, @Param("pageUtil") PageUtil pageUtil);
    Category selectOne(@Param("categoryId") Integer categoryId);
    Integer selectTotal(@Param("categoryName") String categoryName);
}