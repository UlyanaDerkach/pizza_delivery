package com.epam.pizza_delivery.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class OrderItem extends Entity {
    private static final long SerialVersionUID = 10L;
    private long orderId;
    private long userId;
    private String productName;
    private int productAmount;
    private LocalDate deliveryDate;
    private LocalTime deliveryTime;
    private int totalCost;
    private String picturePath;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }


    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return orderId == orderItem.orderId &&
                userId == orderItem.userId &&
                productAmount == orderItem.productAmount &&
                totalCost == orderItem.totalCost &&
                Objects.equals(productName, orderItem.productName) &&
                Objects.equals(deliveryDate, orderItem.deliveryDate) &&
                Objects.equals(deliveryTime, orderItem.deliveryTime) &&
                Objects.equals(picturePath, orderItem.picturePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, productName, productAmount, deliveryDate, deliveryTime, totalCost, picturePath);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", productName='" + productName + '\'' +
                ", productAmount=" + productAmount +
                ", deliveryDate=" + deliveryDate +
                ", deliveryTime=" + deliveryTime +
                ", totalCost=" + totalCost +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
