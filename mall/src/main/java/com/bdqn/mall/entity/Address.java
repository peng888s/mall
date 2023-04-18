package com.bdqn.mall.entity;

public class Address {
    private String addressAreaId;
    private String addressName;
    private Address addressRegionId;

    public Address() {
    }

    public Address(String addressAreaId) {
        this.addressAreaId = addressAreaId;
    }

    public String getAddressAreaId() {
        return addressAreaId;
    }

    public void setAddressAreaId(String addressAreaId) {
        this.addressAreaId = addressAreaId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Address getAddressRegionId() {
        return addressRegionId;
    }

    public void setAddressRegionId(Address addressRegionId) {
        this.addressRegionId = addressRegionId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressAreaId='" + addressAreaId + '\'' +
                ", addressName='" + addressName + '\'' +
                ", addressRegionId=" + addressRegionId +
                '}';
    }

    public Address(String addressAreaId, String addressName, Address addressRegionId) {
        this.addressAreaId = addressAreaId;
        this.addressName = addressName;
        this.addressRegionId = addressRegionId;
    }


}
