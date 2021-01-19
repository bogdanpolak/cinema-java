package com.exercise.cinema.models;

import javax.persistence.*;

@Entity(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketid")
    private int ticketId;
    @Column(name = "showid")
    private String showId;
    @Column(name = "rownum")
    private int rowNum;
    @Column(name = "seatnum")
    private int seatNum;
    private double price; // NUMERIC (14, 2)

    public Ticket() {
    }

    public Ticket(String showId, int rowNum, int seatNum, double price) {
        this.showId = showId;
        this.rowNum = rowNum;
        this.seatNum = seatNum;
        this.price = price;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getShowId() {
        return showId;
    }

    public void setRoomId(String showId) {
        this.showId = showId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
