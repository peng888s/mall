package com.bdqn.mall.entity;

import java.util.Date;
import java.util.List;

public class ProductOrder {
    private Integer productOrderId;
    private String productOrderCode;
    private Address productOrderAddress;
    private String productOrderDetailAddress;
    private String productOrderPost;
    private String productOrderReceiver;
    private String productOrderMobile;
    private Date productOrderPayDate;
    private Date productOrderDeliveryDate;
    private Date productOrderConfirmDate;
    private Byte productOrderStatus;
    private User productOrderUser;
    private List<ProductOrderItem> productOrderItemList;

        public ProductOrder() {
    }

    public ProductOrder(String productOrderCode, String productOrderPost) {
        this.productOrderCode = productOrderCode;
        this.productOrderPost = productOrderPost;
    }

    public ProductOrder(Integer productOrderId, Byte productOrderStatus, Date productOrderDeliveryDate) {
        this.productOrderId = productOrderId;
        this.productOrderDeliveryDate = productOrderDeliveryDate;
        this.productOrderStatus = productOrderStatus;
    }

    public ProductOrder(Integer productOrderId) {
        this.productOrderId = productOrderId;
    }

    public ProductOrder(Integer productOrderId, String productOrderCode, Address productOrderAddress, String productOrderDetailAddress, String productOrderPost, String productOrderReceiver, String productOrderMobile, Date productOrderPayDate, Date productOrderDeliveryDate, Date productOrderConfirmDate, Byte productOrderStatus, User productOrderUser, List<ProductOrderItem> productOrderItemList) {
        this.productOrderId = productOrderId;
        this.productOrderCode = productOrderCode;
        this.productOrderAddress = productOrderAddress;
        this.productOrderDetailAddress = productOrderDetailAddress;
        this.productOrderPost = productOrderPost;
        this.productOrderReceiver = productOrderReceiver;
        this.productOrderMobile = productOrderMobile;
        this.productOrderPayDate = productOrderPayDate;
        this.productOrderDeliveryDate = productOrderDeliveryDate;
        this.productOrderConfirmDate = productOrderConfirmDate;
        this.productOrderStatus = productOrderStatus;
        this.productOrderUser = productOrderUser;
        this.productOrderItemList = productOrderItemList;
    }

    public Integer getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Integer productOrderId) {
        this.productOrderId = productOrderId;
    }

    public String getProductOrderCode() {
        return productOrderCode;
    }

    public void setProductOrderCode(String productOrderCode) {
        this.productOrderCode = productOrderCode;
    }

    public Address getProductOrderAddress() {
        return productOrderAddress;
    }

    public void setProductOrderAddress(Address productOrderAddress) {
        this.productOrderAddress = productOrderAddress;
    }

    public String getProductOrderDetailAddress() {
        return productOrderDetailAddress;
    }

    public void setProductOrderDetailAddress(String productOrderDetailAddress) {
        this.productOrderDetailAddress = productOrderDetailAddress;
    }

    public String getProductOrderPost() {
        return productOrderPost;
    }

    public void setProductOrderPost(String productOrderPost) {
        this.productOrderPost = productOrderPost;
    }

    public String getProductOrderReceiver() {
        return productOrderReceiver;
    }

    public void setProductOrderReceiver(String productOrderReceiver) {
        this.productOrderReceiver = productOrderReceiver;
    }

    public String getProductOrderMobile() {
        return productOrderMobile;
    }

    public void setProductOrderMobile(String productOrderMobile) {
        this.productOrderMobile = productOrderMobile;
    }

    public Date getProductOrderPayDate() {
        return productOrderPayDate;
    }

    public void setProductOrderPayDate(Date productOrderPayDate) {
        this.productOrderPayDate = productOrderPayDate;
    }

    public Date getProductOrderDeliveryDate() {
        return productOrderDeliveryDate;
    }

    public void setProductOrderDeliveryDate(Date productOrderDeliveryDate) {
        this.productOrderDeliveryDate = productOrderDeliveryDate;
    }

    public Date getProductOrderConfirmDate() {
        return productOrderConfirmDate;
    }

    public void setProductOrderConfirmDate(Date productOrderConfirmDate) {
        this.productOrderConfirmDate = productOrderConfirmDate;
    }

    public Byte getProductOrderStatus() {
        return productOrderStatus;
    }

    public void setProductOrderStatus(Byte productOrderStatus) {
        this.productOrderStatus = productOrderStatus;
    }

    public User getProductOrderUser() {
        return productOrderUser;
    }

    public void setProductOrderUser(User productOrderUser) {
        this.productOrderUser = productOrderUser;
    }

    public List<ProductOrderItem> getProductOrderItemList() {
        return productOrderItemList;
    }

    public void setProductOrderItemList(List<ProductOrderItem> productOrderItemList) {
        this.productOrderItemList = productOrderItemList;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "productOrderId=" + productOrderId +
                ", productOrderCode='" + productOrderCode + '\'' +
                ", productOrderAddress=" + productOrderAddress +
                ", productOrderDetailAddress='" + productOrderDetailAddress + '\'' +
                ", productOrderPost='" + productOrderPost + '\'' +
                ", productOrderReceiver='" + productOrderReceiver + '\'' +
                ", productOrderMobile='" + productOrderMobile + '\'' +
                ", productOrderPayDate=" + productOrderPayDate +
                ", productOrderDeliveryDate=" + productOrderDeliveryDate +
                ", productOrderConfirmDate=" + productOrderConfirmDate +
                ", productOrderStatus=" + productOrderStatus +
                ", productOrderUser=" + productOrderUser +
                ", productOrderItemList=" + productOrderItemList +
                '}';
    }
}
