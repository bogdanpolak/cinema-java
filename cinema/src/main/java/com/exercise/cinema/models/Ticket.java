package com.exercise.cinema.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TicketId;
    private int RoomId;
    private int RowNum;
    private int SeatNum;
    private double Price; // NUMERIC (14, 2)
    // TODO: Implement JPA mapping
    // Accessing JPA Data with REST: https://spring.io/guides/gs/accessing-data-rest/
    // Spring Boot With SQLite:  https://www.baeldung.com/spring-boot-sqlite

    public int getTicketId() {
        return TicketId;
    }

    public void setTicketId(int ticketId) {
        TicketId = ticketId;
    }

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int roomId) {
        RoomId = roomId;
    }

    public int getRowNum() {
        return RowNum;
    }

    public void setRowNum(int rowNum) {
        RowNum = rowNum;
    }

    public int getSeatNum() {
        return SeatNum;
    }

    public void setSeatNum(int seatNum) {
        SeatNum = seatNum;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

}
