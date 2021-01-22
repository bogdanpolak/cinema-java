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
        // ---- Validation: Show Key ----
        var show = showRepository.findById(request.showKey);
        if (show.isEmpty())
            throw new InvalidParameterException(String.format(
                "Show Key '%s' is invalid and can't be found in database",
                request.showKey));
        // ---- Get room rows and seats ----
        var roomId = show.get().getRoomId();
        var room = roomRepository.findById(roomId).get();
        var rows = room.getRows();
        var seats = room.getColumns();
        // ---- Validate seat position ----
        if (request.rowNum<1 || request.rowNum>rows)
            throw new InvalidParameterException(String.format(
                "Incorrect row value: %d. In room '%s' there are %d rows.",
                request.rowNum, room.getName(), rows));
        if (request.seatNum<1 || request.seatNum>seats)
            throw new InvalidParameterException(String.format(
                "Incorrect seat value: %d. In row %d there are %d seats.",
                request.seatNum, request.rowNum, seats));
        // ---- Verification is seat booked ----
        Ticket existingTicket = ticketRepository.findBySeat(
                request.showKey,
                request.rowNum,
                request.seatNum);
        if (existingTicket != null)
            throw new InvalidParameterException(String.format(
                "Seat (rowNum:%d, seatNum:%d) already booked, please choose other.",
                request.rowNum, request.seatNum));
        // ---- Sell ticket ----
        var ticket = new Ticket(request.showKey,
                request.rowNum, request.seatNum,
                calcTicketPrice(request.rowNum,rows));
        // ---- Store ticket ----
        ticketRepository.saveAndFlush(ticket);
    }
}
