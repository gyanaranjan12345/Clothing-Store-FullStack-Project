package com.clothingstore.ClothingStore.dto;


public class CheckoutRequest {

    private String fullName;

    private String phoneNumber;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String pincode;

    private String paymentMethod;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(
            String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(
            String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(
            String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(
            String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(
            String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(
            String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(
            String pincode) {
        this.pincode = pincode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(
            String paymentMethod) {
        this.paymentMethod =
                paymentMethod;
    }
}