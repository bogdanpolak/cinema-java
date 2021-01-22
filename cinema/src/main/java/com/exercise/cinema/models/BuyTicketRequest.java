package com.exercise.cinema.models;

public class BuyTicketRequest {
    private String showKey;
    private int rowNum;
    private int seatNum;
    private double price;

    public String getShowKey() {
        return showKey;
    }

    public void setShowKey(String showKey) {
        this.showKey = showKey;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

}
