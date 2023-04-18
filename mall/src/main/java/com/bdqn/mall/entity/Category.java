package com.bdqn.mall.entity;

import java.util.List;

public class Category {
    private Integer categoryId;
    private String categoryName;
    private String categoryImageSrc;
    //产品列表
    private List<Product> productList;
    //产品二维集合
    private List<List<Product>> complexProductList;
    //属性列表
    private List<Property> propertyList;

    public Category(Integer categoryId, String categoryName, String categoryImageSrc) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryImageSrc = categoryImageSrc;
    }

    public Category(String categoryName, String categoryImageSrc) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryImageSrc = categoryImageSrc;
    }

    public Category() {
    }

    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImageSrc() {
        return categoryImageSrc;
    }

    public void setCategoryImageSrc(String categoryImageSrc) {
        this.categoryImageSrc = categoryImageSrc;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<List<Product>> getComplexProductList() {
        return complexProductList;
    }

    public void setComplexProductList(List<List<Product>> complexProductList) {
        this.complexProductList = complexProductList;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryImageSrc='" + categoryImageSrc + '\'' +
                ", productList=" + productList +
                ", complexProductList=" + complexProductList +
                ", propertyList=" + propertyList +
                '}';
    }
}
