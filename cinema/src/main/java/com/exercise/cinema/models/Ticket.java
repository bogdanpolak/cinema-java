package com.exercise.cinema.models;

public class Ticket {
    int TicketId;
    int RoomId;
    int RowNum;
    int SeatNum;
    double Price; // NUMERIC (14, 2)
    // TODO: Implement JPA mapping
    // Accessing JPA Data with REST: https://spring.io/guides/gs/accessing-data-rest/
    // Spring Boot With SQLite:  https://www.baeldung.com/spring-boot-sqlite
}
