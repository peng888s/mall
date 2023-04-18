package com.bdqn.mall.entity;

public class PropertyValue {
    private Integer propertyValueId;
    private String propertyValueValue;
    private Property propertyValueProperty;
    private Product propertyValueProduct;

    public PropertyValue() {
    }

    public PropertyValue(Integer propertyValueId, String propertyValueValue, Property propertyValueProperty, Product propertyValueProduct) {
        this.propertyValueId = propertyValueId;
        this.propertyValueValue = propertyValueValue;
        this.propertyValueProperty = propertyValueProperty;
        this.propertyValueProduct = propertyValueProduct;
    }

    public PropertyValue(Integer propertyValueId) {
        this.propertyValueId = propertyValueId;
    }

    public PropertyValue(String propertyValueValue, Property propertyValueProperty, Product propertyValueProduct) {
        this.propertyValueValue = propertyValueValue;
        this.propertyValueProperty = propertyValueProperty;
        this.propertyValueProduct = propertyValueProduct;
    }

    public PropertyValue(String propertyValueValue, Integer propertyValueId) {
        this.propertyValueValue = propertyValueValue;
        this.propertyValueId = propertyValueId;
    }

    public Integer getPropertyValueId() {
        return propertyValueId;
    }

    public void setPropertyValueId(Integer propertyValueId) {
        this.propertyValueId = propertyValueId;
    }

    public String getPropertyValueValue() {
        return propertyValueValue;
    }

    public void setPropertyValueValue(String propertyValueValue) {
        this.propertyValueValue = propertyValueValue;
    }

    public Property getPropertyValueProperty() {
        return propertyValueProperty;
    }

    public void setPropertyValueProperty(Property propertyValueProperty) {
        this.propertyValueProperty = propertyValueProperty;
    }

    public Product getPropertyValueProduct() {
        return propertyValueProduct;
    }

    public void setPropertyValueProduct(Product propertyValueProduct) {
        this.propertyValueProduct = propertyValueProduct;
    }

    @Override
    public String toString() {
        return "PropertyValue{" +
                "propertyValueId=" + propertyValueId +
                ", propertyValueValue='" + propertyValueValue + '\'' +
                ", propertyValueProperty=" + propertyValueProperty +
                ", propertyValueProduct=" + propertyValueProduct +
                '}';
    }
}
