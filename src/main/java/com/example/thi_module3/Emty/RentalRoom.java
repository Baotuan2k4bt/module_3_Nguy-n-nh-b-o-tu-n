package com.example.thi_module3.Emty;

import java.util.Date;

public class RentalRoom {
    private int roomID;
    private String tenantName;
    private String phoneNumber;
    private Date rentalStartDate;
    private String paymentMethodName;
    private String notes;

    public RentalRoom() {
    }

    public RentalRoom(int roomID, String tenantName, String phoneNumber, Date rentalStartDate, String paymentMethodName, String notes) {
        this.roomID = roomID;
        this.tenantName = tenantName;
        this.phoneNumber = phoneNumber;
        this.rentalStartDate = rentalStartDate;
        this.paymentMethodName = paymentMethodName;
        this.notes = notes;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(Date rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
