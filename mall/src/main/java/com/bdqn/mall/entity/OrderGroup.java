package com.bdqn.mall.entity;

import java.util.Date;

public class OrderGroup {
    private Date productOrderPayDate;
    private Integer productOrderCount;
    private Byte productOrderStatus;

    public OrderGroup(Date productOrderPayDate, Integer productOrderCount, Byte productOrderStatus) {
        this.productOrderPayDate = productOrderPayDate;
        this.productOrderCount = productOrderCount;
        this.productOrderStatus = productOrderStatus;
    }

    public OrderGroup() {
    }

    @Override
    public String toString() {
        return "OrderGroup{" +
                "productOrderPayDate=" + productOrderPayDate +
                ", productOrderCount=" + productOrderCount +
                ", productOrderStatus=" + productOrderStatus +
                '}';
    }

    public Date getProductOrderPayDate() {
        return productOrderPayDate;
    }

    public void setProductOrderPayDate(Date productOrderPayDate) {
        this.productOrderPayDate = productOrderPayDate;
    }

    public Integer getProductOrderCount() {
        return productOrderCount;
    }

    public void setProductOrderCount(Integer productOrderCount) {
        this.productOrderCount = productOrderCount;
    }

    public Byte getProductOrderStatus() {
        return productOrderStatus;
    }

    public void setProductOrderStatus(Byte productOrderStatus) {
        this.productOrderStatus = productOrderStatus;
    }
}
