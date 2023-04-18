package com.bdqn.mall.service.impl;

import com.bdqn.mall.dao.ReviewMapper;
import com.bdqn.mall.entity.Review;
import com.bdqn.mall.service.ReviewService;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Resource
    private ReviewMapper reviewMapper;

    public void setReviewMapper(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(Review review) {
        return reviewMapper.insertOne(review)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(Review review) {
        return reviewMapper.updateOne(review)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteList(Integer[] reviewIdList) {
        return reviewMapper.deleteList(reviewIdList)>0;
    }

    @Override
    public List<Review> getList(Review review, PageUtil pageUtil) {
        return reviewMapper.select(review,pageUtil);
    }

    @Override
    public List<Review> getListByUserId(Integer userId, PageUtil pageUtil) {
        return reviewMapper.selectByUserId(userId,pageUtil);
    }

    @Override
    public List<Review> getListByProductId(Integer productId, PageUtil pageUtil) {
        return reviewMapper.selectByProductId(productId,pageUtil);
    }

    @Override
    public Review get(Integer reviewId) {
        return reviewMapper.selectOne(reviewId);
    }

    @Override
    public Integer getTotal(Review review) {
        return reviewMapper.selectTotal(review);
    }

    @Override
    public Integer getTotalByUserId(Integer userId) {
        return reviewMapper.selectTotalByUserId(userId);
    }

    @Override
    public Integer getTotalByProductId(Integer productId) {
        return reviewMapper.selectTotalByProductId(productId);
    }

    @Override
    public Integer getTotalByOrderItemId(Integer productOrderItemId) {
        return reviewMapper.selectTotalByOrderItemId(productOrderItemId);
    }
}
