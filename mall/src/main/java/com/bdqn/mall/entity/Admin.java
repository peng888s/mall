package com.bdqn.mall.entity;

public class Admin {
    private Integer adminId;
    private String adminName;
    private String adminNickName;
    private String adminPassword;
    private String adminProfilePictureSrc;

    public Admin(Integer adminId, String adminName, String adminNickName, String adminPassword, String adminProfilePictureSrc) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminNickName = adminNickName;
        this.adminPassword = adminPassword;
        this.adminProfilePictureSrc = adminProfilePictureSrc;
    }

    public Admin(Integer adminId) {
        this.adminId = adminId;
    }
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminNickName() {
        return adminNickName;
    }

    public void setAdminNickName(String adminNickName) {
        this.adminNickName = adminNickName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminProfilePictureSrc() {
        return adminProfilePictureSrc;
    }

    public void setAdminProfilePictureSrc(String adminProfilePictureSrc) {
        this.adminProfilePictureSrc = adminProfilePictureSrc;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminNickName='" + adminNickName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", adminProfilePictureSrc='" + adminProfilePictureSrc + '\'' +
                '}';
    }

    public Admin() {
    }
}
