package com.bdqn.mall.entity;

public class ProductImage {
    private Integer productImageId;
    private Byte productImageType;
    private String productImageSrc;
    private Product productImageProduct;

    public ProductImage(){

    }

    public ProductImage(Integer productImageId, Byte productImageType, String productImageSrc, Product productImageProduct) {
        this.productImageId = productImageId;
        this.productImageType = productImageType;
        this.productImageSrc = productImageSrc;
        this.productImageProduct = productImageProduct;
    }

    public ProductImage(Integer productImageId) {
        this.productImageId = productImageId;
    }

    public ProductImage(Byte productImageType, String productImageSrc, Product productImageProduct) {
        this.productImageType = productImageType;
        this.productImageSrc = productImageSrc;
        this.productImageProduct = productImageProduct;
    }

    public Integer getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(Integer productImageId) {
        this.productImageId = productImageId;
    }

    public Byte getProductImageType() {
        return productImageType;
    }

    public void setProductImageType(Byte productImageType) {
        this.productImageType = productImageType;
    }

    public String getProductImageSrc() {
        return productImageSrc;
    }

    public void setProductImageSrc(String productImageSrc) {
        this.productImageSrc = productImageSrc;
    }

    public Product getProductImageProduct() {
        return productImageProduct;
    }

    public void setProductImageProduct(Product productImageProduct) {
        this.productImageProduct = productImageProduct;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "productImageId=" + productImageId +
                ", productImageType=" + productImageType +
                ", productImageSrc='" + productImageSrc + '\'' +
                ", productImageProduct=" + productImageProduct +
                '}';
    }
}
