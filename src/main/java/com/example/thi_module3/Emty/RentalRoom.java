package com.example.thi_module3.Emty;

import java.time.LocalDate;


public class RentalRoom {
    private int roomID;
    private String tenantName;
    private String phoneNumber;
    private LocalDate rentalStartDate;
    private int paymentMethodID;
    private String paymentMethodName;

    private String notes;

    public RentalRoom() {
    }

    public RentalRoom(int roomID, String tenantName, String phoneNumber, LocalDate rentalStartDate, String paymentMethodName, String notes) {
        this.roomID = roomID;
        this.tenantName = tenantName;
        this.phoneNumber = phoneNumber;
        this.rentalStartDate = rentalStartDate;
        this.paymentMethodName = paymentMethodName;
        this.notes = notes;

    }
    public RentalRoom(String tenantName, String phoneNumber, LocalDate rentalStartDate, int paymentMethodID, String notes) {
        this.tenantName = tenantName;
        this.phoneNumber = phoneNumber;
        this.rentalStartDate = rentalStartDate;
        this.paymentMethodID = paymentMethodID;
        this.notes = notes;
    }

    public RentalRoom(int roomID, String tenantName, String phoneNumber, LocalDate rentalStartDate, int paymentMethodID, String notes) {
        this.roomID = roomID;
        this.tenantName = tenantName;
        this.phoneNumber = phoneNumber;
        this.rentalStartDate = rentalStartDate;
        this.paymentMethodID = paymentMethodID;
        this.notes = notes;
    }



    public RentalRoom(String roomID, String tenantName, String phoneNumber, LocalDate rentalStartDate, int paymentMethodID , String notes) {
        this.roomID = Integer.parseInt(roomID);
        this.tenantName = tenantName;
        this.phoneNumber = phoneNumber;
        this.rentalStartDate = rentalStartDate;
        this.paymentMethodID = paymentMethodID;
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

    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public int getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setRentalStartDate(LocalDate rentalStartDate) {
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
