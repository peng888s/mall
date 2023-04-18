package com.bdqn.mall.dao;

import com.bdqn.mall.entity.ProductImage;
import com.bdqn.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ProductImageMapper {
    Integer insertOne(@Param("productImage") ProductImage productImage);

    Integer insertList(@Param("productImageList") List<ProductImage> productImageList);
    Integer updateOne(@Param("productImage") ProductImage productImage);
    Integer deleteList(@Param("productImageIdList") Integer[] productImageIdList);

    List<ProductImage> select(@Param("productId") Integer productId, @Param("productImageType") Byte productImageType, @Param("pageUtil") PageUtil pageUtil);
    ProductImage selectOne(@Param("productImageId") Integer productImageId);
    Integer selectTotal(@Param("productId") Integer productId, @Param("productImageType") Byte productImageType);
}
