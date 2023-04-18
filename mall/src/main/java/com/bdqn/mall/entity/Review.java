package com.bdqn.mall.entity;

import java.util.Date;

public class Review {
    private Integer reviewId;
    private String reviewContent;
    private Date reviewCreateDate;
    private User reviewUser;
    private Product reviewProduct;
    private ProductOrderItem reviewOrderItem;

    public Review() {
    }

    public Review(Integer reviewId, String reviewContent, Date reviewCreateDate, User reviewUser, Product reviewProduct, ProductOrderItem reviewOrderItem) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.reviewCreateDate = reviewCreateDate;
        this.reviewUser = reviewUser;
        this.reviewProduct = reviewProduct;
        this.reviewOrderItem = reviewOrderItem;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Date getReviewCreateDate() {
        return reviewCreateDate;
    }

    public void setReviewCreateDate(Date reviewCreateDate) {
        this.reviewCreateDate = reviewCreateDate;
    }

    public User getReviewUser() {
        return reviewUser;
    }

    public void setReviewUser(User reviewUser) {
        this.reviewUser = reviewUser;
    }

    public Product getReviewProduct() {
        return reviewProduct;
    }

    public void setReviewProduct(Product reviewProduct) {
        this.reviewProduct = reviewProduct;
    }

    public ProductOrderItem getReviewOrderItem() {
        return reviewOrderItem;
    }

    public void setReviewOrderItem(ProductOrderItem reviewOrderItem) {
        this.reviewOrderItem = reviewOrderItem;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", reviewContent='" + reviewContent + '\'' +
                ", reviewCreateDate=" + reviewCreateDate +
                ", reviewUser=" + reviewUser +
                ", reviewProduct=" + reviewProduct +
                ", reviewOrderItem=" + reviewOrderItem +
                '}';
    }
}
