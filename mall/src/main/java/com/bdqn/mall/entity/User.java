package com.bdqn.mall.entity;

import java.util.Date;
import java.util.List;

public class User {
    private Integer userId;
    private String userName;
    private String userNickName;
    private String userPassword;
    private String userRealName;
    private Byte userGender;
    private Date userBirthday;
    private Address userAddress;
    private Address userHomePlace;
    private String userProfilePictureSrc;
    private List<Review> reviewList;
    private List<ProductOrderItem> productOrderItemList;
    private List<ProductOrder> productOrderList;


    public User() {
    }

    public User(Integer userId, String userName, String userNickName, String userPassword, String userRealName, Byte userGender, Date userBirthday, Address userAddress, Address userHomePlace, String userProfilePictureSrc, List<Review> reviewList, List<ProductOrderItem> productOrderItemList, List<ProductOrder> productOrderList) {
        this.userId = userId;
        this.userName = userName;
        this.userNickName = userNickName;
        this.userPassword = userPassword;
        this.userRealName = userRealName;
        this.userGender = userGender;
        this.userBirthday = userBirthday;
        this.userAddress = userAddress;
        this.userHomePlace = userHomePlace;
        this.userProfilePictureSrc = userProfilePictureSrc;
        this.reviewList = reviewList;
        this.productOrderItemList = productOrderItemList;
        this.productOrderList = productOrderList;
    }

    public User(Integer userId){
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public Byte getUserGender() {
        return userGender;
    }

    public void setUserGender(Byte userGender) {
        this.userGender = userGender;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Address getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Address userAddress) {
        this.userAddress = userAddress;
    }

    public Address getUserHomePlace() {
        return userHomePlace;
    }

    public void setUserHomePlace(Address userHomePlace) {
        this.userHomePlace = userHomePlace;
    }

    public String getUserProfilePictureSrc() {
        return userProfilePictureSrc;
    }

    public void setUserProfilePictureSrc(String userProfilePictureSrc) {
        this.userProfilePictureSrc = userProfilePictureSrc;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<ProductOrderItem> getProductOrderItemList() {
        return productOrderItemList;
    }

    public void setProductOrderItemList(List<ProductOrderItem> productOrderItemList) {
        this.productOrderItemList = productOrderItemList;
    }

    public List<ProductOrder> getProductOrderList() {
        return productOrderList;
    }

    public void setProductOrderList(List<ProductOrder> productOrderList) {
        this.productOrderList = productOrderList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userGender=" + userGender +
                ", userBirthday=" + userBirthday +
                ", userAddress=" + userAddress +
                ", userHomePlace=" + userHomePlace +
                ", userProfilePictureSrc='" + userProfilePictureSrc + '\'' +
                ", reviewList=" + reviewList +
                ", productOrderItemList=" + productOrderItemList +
                ", productOrderList=" + productOrderList +
                '}';
    }
}
