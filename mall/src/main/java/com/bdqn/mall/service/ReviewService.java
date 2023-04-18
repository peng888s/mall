package com.bdqn.mall.service;

import com.bdqn.mall.entity.Review;
import com.bdqn.mall.util.PageUtil;

import java.util.List;

public interface ReviewService {
    boolean add(Review review);
    boolean update(Review review);
    boolean deleteList(Integer[] reviewIdList);

    List<Review> getList(Review review, PageUtil pageUtil);
    List<Review> getListByUserId(Integer userId, PageUtil pageUtil);
    List<Review> getListByProductId(Integer productId, PageUtil pageUtil);
    Review get(Integer reviewId);
    Integer getTotal(Review review);
    Integer getTotalByUserId(Integer userId);
    Integer getTotalByProductId(Integer productId);

    Integer getTotalByOrderItemId(Integer productOrderItemId);
}
