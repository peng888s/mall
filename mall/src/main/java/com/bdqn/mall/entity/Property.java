package com.bdqn.mall.entity;

import java.util.List;

public class Property {
    private Integer propertyId;
    private String propertyName;
    private Category propertyCategory;
    private List<PropertyValue> propertyValueList;

    public Property() {
    }

    public Property(Integer propertyId, String propertyName, Category propertyCategory, List<PropertyValue> propertyValueList) {
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.propertyCategory = propertyCategory;
        this.propertyValueList = propertyValueList;
    }

    public Property(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Category getPropertyCategory() {
        return propertyCategory;
    }

    public void setPropertyCategory(Category propertyCategory) {
        this.propertyCategory = propertyCategory;
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void setPropertyValueList(List<PropertyValue> propertyValueList) {
        this.propertyValueList = propertyValueList;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyId=" + propertyId +
                ", propertyName='" + propertyName + '\'' +
                ", propertyCategory=" + propertyCategory +
                ", propertyValueList=" + propertyValueList +
                '}';
    }
}
