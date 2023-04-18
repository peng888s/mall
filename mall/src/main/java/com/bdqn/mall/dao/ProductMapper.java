package com.bdqn.mall.dao;

import com.bdqn.mall.entity.Product;
import com.bdqn.mall.util.OrderUtil;
import com.bdqn.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ProductMapper {
    Integer insertOne(@Param("product") Product product);
    Integer updateOne(@Param("product") Product product);

    List<Product> select(@Param("product") Product product, @Param("productIsEnabledArray") Byte[] productIsEnabledArray, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);

    List<Product> selectTitle(@Param("product") Product product, @Param("pageUtil") PageUtil pageUtil);
    Product selectOne(@Param("productId") Integer productId);
    Integer selectTotal(@Param("product") Product product, @Param("productIsEnabledArray") Byte[] productIsEnabledArray);

    List<Product> selectMoreList(@Param("product") Product product, @Param("productIsEnabledArray") Byte[] bytes, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil, @Param("productNameSplit") String[] productNameSplit);

    Integer selectMoreListTotal(@Param("product") Product product, @Param("productIsEnabledArray") Byte[] productIsEnabledArray, @Param("productNameSplit") String[] productNameSplit);
}
