package com.exercise.cinema.controllers;

import com.exercise.cinema.models.*;
import com.exercise.cinema.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
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

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTicket(@RequestBody TicketRequestDto request) {
        // ---- Validation: Show Key ----
        var show = showRepository.findById(request.showKey);
        if (show.isEmpty()) throw new InvalidParameterException(String.format(
                "Show Key '%s' is invalid and can't be found in database",
                request.showKey));
        // ---- Get room rows and seats ----
        var roomId = show.get().getRoomId();
        var room = roomRepository.findById(roomId).get();
        var rows = room.getRows();
        var seats = room.getColumns();
        Range<Integer> rowRange = Range.from(Range.Bound.inclusive(1)).to(Range.Bound.inclusive(rows));
        Range<Integer> seatRange = Range.from(Range.Bound.inclusive(1)).to(Range.Bound.inclusive(seats));
        if (!rowRange.contains(request.rowNum) || !seatRange.contains(request.seatNum))
            throw new InvalidParameterException(
                    String.format("Incorrect seat position (rowNum:%d, seatNum:%d) in room '%s'",
                            request.rowNum, request.seatNum, room.getName()));
        Ticket existingTicket = ticketRepository.findBySeat(request.showKey, request.rowNum, request.seatNum);
        if (existingTicket != null) throw new InvalidParameterException(
                "Seat already sold, please choose other");
        var ticket = new Ticket(request.showKey, request.rowNum, request.seatNum, 15.00);
        ticketRepository.saveAndFlush(ticket);
    }
}
