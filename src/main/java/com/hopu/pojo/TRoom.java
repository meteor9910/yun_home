package com.hopu.pojo;

import java.util.Date;

public class TRoom {
    private Integer id;

    private String title;

    private Integer rent;

    private String roomType;

    private Integer userId;

    private Integer regionId;

    private String address;

    private String detailAddress;

    private Double area;

    private String rentType;

    private String trafficCondition;

    private String facilitiesCondition;

    private String roomDesc;

    private Boolean rentStatus;

    private Integer rentUserId;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType == null ? null : roomType.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType == null ? null : rentType.trim();
    }

    public String getTrafficCondition() {
        return trafficCondition;
    }

    public void setTrafficCondition(String trafficCondition) {
        this.trafficCondition = trafficCondition == null ? null : trafficCondition.trim();
    }

    public String getFacilitiesCondition() {
        return facilitiesCondition;
    }

    public void setFacilitiesCondition(String facilitiesCondition) {
        this.facilitiesCondition = facilitiesCondition == null ? null : facilitiesCondition.trim();
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc == null ? null : roomDesc.trim();
    }

    public Boolean getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(Boolean rentStatus) {
        this.rentStatus = rentStatus;
    }

    public Integer getRentUserId() {
        return rentUserId;
    }

    public void setRentUserId(Integer rentUserId) {
        this.rentUserId = rentUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}