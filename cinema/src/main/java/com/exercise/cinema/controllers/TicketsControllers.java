package com.exercise.cinema.controllers;

import com.exercise.cinema.models.*;
import com.exercise.cinema.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.security.InvalidParameterException;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketsControllers {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ShowRepository showRepository;

    @GetMapping
    public List<Ticket> list(){
        return ticketRepository.findAll();
    }

    private double calcTicketPrice(int rowNum, int rows) {
        return (rowNum <= rows/2) ? 15.00 : 10.00;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void buyTicket(@RequestBody BuyTicketRequest request) throws InvalidParameterException {
        var showKey = request.getShowKey();
        var rowNum = request.getRowNum();
        var seatNum = request.getSeatNum();
        // ---- Validation: Show Key ----
        var show = showRepository.findById(showKey);
        if (show.isEmpty())
            throw new InvalidParameterException(String.format(
                "Show Key '%s' is invalid and can't be found in database",
                showKey));
        // ---- Get room rows and seats ----
        var roomId = show.get().getRoomId();
        var room = roomRepository.findById(roomId).get();
        var rows = room.getRows();
        var seats = room.getColumns();
        // ---- Validate seat position ----
        if (rowNum<1 || rowNum>rows)
            throw new InvalidParameterException(String.format(
                "Incorrect row value: %d. In room '%s' there are %d rows.",
                rowNum, room.getName(), rows));
        if (seatNum<1 || seatNum>seats)
            throw new InvalidParameterException(String.format(
                "Incorrect seat value: %d. In row %d there are %d seats.",
                seatNum, rowNum, seats));
        // ---- Verification is seat booked ----
        Ticket existingTicket = ticketRepository.findBySeat(
                showKey, rowNum, seatNum);
        if (existingTicket != null)
            throw new InvalidParameterException(String.format(
                "Seat (rowNum:%d, seatNum:%d) already booked, please choose other.",
                rowNum, seatNum));
        // ---- Sell ticket ----
        var ticket = new Ticket(showKey, rowNum, seatNum, request.getPrice());
        // ---- Store ticket ----
        ticketRepository.saveAndFlush(ticket);
    }
}
