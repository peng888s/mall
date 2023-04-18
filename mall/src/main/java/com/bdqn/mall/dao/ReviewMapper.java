package com.bdqn.mall.dao;

import com.bdqn.mall.entity.Review;
import com.bdqn.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ReviewMapper {
    Integer insertOne(@Param("review") Review review);
    Integer updateOne(@Param("review") Review review);
    Integer deleteList(@Param("reviewIdList") Integer[] reviewIdList);

    List<Review> select(@Param("review") Review review, @Param("pageUtil") PageUtil pageUtil);
    List<Review> selectByUserId(@Param("userId") Integer userId, @Param("pageUtil") PageUtil pageUtil);
    List<Review> selectByProductId(@Param("productId") Integer productId, @Param("pageUtil") PageUtil pageUtil);
    Review selectOne(@Param("reviewId") Integer reviewId);
    Integer selectTotal(@Param("review") Review review);
    Integer selectTotalByUserId(@Param("userId") Integer userId);
    Integer selectTotalByProductId(@Param("productId") Integer productId);

    Integer selectTotalByOrderItemId(@Param("productOrderItemId") Integer productOrderItemId);
}
