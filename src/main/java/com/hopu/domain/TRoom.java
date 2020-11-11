package com.hopu.domain;


public class TRoom {

  private long id;
  private String title;
  private long rent;
  private String roomType;
  private long userId;
  private long regionId;
  private String address;
  private String detailAddress;
  private double area;
  private String rentType;
  private String trafficCondition;
  private String facilitiesCondition;
  private String roomDesc;
  private long rentStatus;
  private long rentUserId;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public long getRent() {
    return rent;
  }

  public void setRent(long rent) {
    this.rent = rent;
  }


  public String getRoomType() {
    return roomType;
  }

  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getRegionId() {
    return regionId;
  }

  public void setRegionId(long regionId) {
    this.regionId = regionId;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getDetailAddress() {
    return detailAddress;
  }

  public void setDetailAddress(String detailAddress) {
    this.detailAddress = detailAddress;
  }


  public double getArea() {
    return area;
  }

  public void setArea(double area) {
    this.area = area;
  }


  public String getRentType() {
    return rentType;
  }

  public void setRentType(String rentType) {
    this.rentType = rentType;
  }


  public String getTrafficCondition() {
    return trafficCondition;
  }

  public void setTrafficCondition(String trafficCondition) {
    this.trafficCondition = trafficCondition;
  }


  public String getFacilitiesCondition() {
    return facilitiesCondition;
  }

  public void setFacilitiesCondition(String facilitiesCondition) {
    this.facilitiesCondition = facilitiesCondition;
  }


  public String getRoomDesc() {
    return roomDesc;
  }

  public void setRoomDesc(String roomDesc) {
    this.roomDesc = roomDesc;
  }


  public long getRentStatus() {
    return rentStatus;
  }

  public void setRentStatus(long rentStatus) {
    this.rentStatus = rentStatus;
  }


  public long getRentUserId() {
    return rentUserId;
  }

  public void setRentUserId(long rentUserId) {
    this.rentUserId = rentUserId;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }

}
