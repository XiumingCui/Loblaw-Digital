package com.loblaw;

import java.util.Date;

public class Data {
    private String pcAccount;
    private String uuid;
    private String id;
    private Date date;
    private int storeId;
    private double orderValue;
    private String orderId;

    public String getPcAccount() {
        return pcAccount;
    }

    public String getUuid() {
        return uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if (hasPcAccount()) {
            String[] ids = id.split("&&");
            this.pcAccount = ids[0];
            this.uuid = ids[1];
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public double getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(double orderValue) {
        this.orderValue = orderValue;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean hasPcAccount() {
        return this.id.contains("&&");
    }
}
