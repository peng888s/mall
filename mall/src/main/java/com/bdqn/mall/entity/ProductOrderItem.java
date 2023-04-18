package com.bdqn.mall.entity;

public class ProductOrderItem {
    private Integer productOrderItemId;
    private Short productOrderItemNumber;
    private Double productOrderItemPrice;
    private Product productOrderItemProduct;
    private ProductOrder productOrderItemOrder;
    private User productOrderItemUser;
    private String productOrderItemUserMessage;
    //订单产品是否已经评价
    private Boolean isReview;

    public ProductOrderItem() {
    }

    public ProductOrderItem(Integer productOrderItemId, Short productOrderItemNumber, Double productOrderItemPrice, Product productOrderItemProduct, ProductOrder productOrderItemOrder, User productOrderItemUser, String productOrderItemUserMessage, Boolean isReview) {
        this.productOrderItemId = productOrderItemId;
        this.productOrderItemNumber = productOrderItemNumber;
        this.productOrderItemPrice = productOrderItemPrice;
        this.productOrderItemProduct = productOrderItemProduct;
        this.productOrderItemOrder = productOrderItemOrder;
        this.productOrderItemUser = productOrderItemUser;
        this.productOrderItemUserMessage = productOrderItemUserMessage;
        this.isReview = isReview;
    }

    public ProductOrderItem(Integer productOrderItemId) {
        this.productOrderItemId = productOrderItemId;
    }
    public Integer getProductOrderItemId() {
        return productOrderItemId;
    }

    public void setProductOrderItemId(Integer productOrderItemId) {
        this.productOrderItemId = productOrderItemId;
    }

    public Short getProductOrderItemNumber() {
        return productOrderItemNumber;
    }

    public void setProductOrderItemNumber(Short productOrderItemNumber) {
        this.productOrderItemNumber = productOrderItemNumber;
    }

    public Double getProductOrderItemPrice() {
        return productOrderItemPrice;
    }

    public void setProductOrderItemPrice(Double productOrderItemPrice) {
        this.productOrderItemPrice = productOrderItemPrice;
    }

    public Product getProductOrderItemProduct() {
        return productOrderItemProduct;
    }

    public void setProductOrderItemProduct(Product productOrderItemProduct) {
        this.productOrderItemProduct = productOrderItemProduct;
    }

    public ProductOrder getProductOrderItemOrder() {
        return productOrderItemOrder;
    }

    public void setProductOrderItemOrder(ProductOrder productOrderItemOrder) {
        this.productOrderItemOrder = productOrderItemOrder;
    }

    public User getProductOrderItemUser() {
        return productOrderItemUser;
    }

    public void setProductOrderItemUser(User productOrderItemUser) {
        this.productOrderItemUser = productOrderItemUser;
    }

    public String getProductOrderItemUserMessage() {
        return productOrderItemUserMessage;
    }

    public void setProductOrderItemUserMessage(String productOrderItemUserMessage) {
        this.productOrderItemUserMessage = productOrderItemUserMessage;
    }

    public Boolean getIsReview() {
        return isReview;
    }

    public void setIsReview(Boolean review) {
        isReview = review;
    }

    @Override
    public String toString() {
        return "ProductOrderItem{" +
                "productOrderItemId=" + productOrderItemId +
                ", productOrderItemNumber=" + productOrderItemNumber +
                ", productOrderItemPrice=" + productOrderItemPrice +
                ", productOrderItemProduct=" + productOrderItemProduct +
                ", productOrderItemOrder=" + productOrderItemOrder +
                ", productOrderItemUser=" + productOrderItemUser +
                ", productOrderItemUserMessage='" + productOrderItemUserMessage + '\'' +
                ", isReview=" + isReview +
                '}';
    }
}
