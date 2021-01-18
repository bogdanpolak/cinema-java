package com.exercise.cinema.controllers;

import com.exercise.cinema.models.Ticket;
import com.exercise.cinema.repositories.TicketRepository;
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

    @GetMapping
    public List<Ticket> list(){
        return ticketRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTicket(@RequestBody TicketRequestDto request) {
        Range<Integer> rowRange = Range.from(Range.Bound.inclusive(1)).to(Range.Bound.inclusive(6));
        Range<Integer> seatRange = Range.from(Range.Bound.inclusive(1)).to(Range.Bound.inclusive(20));
        var keyHasText = StringUtils.hasText(request.showKey);
        var rowHasValidRange = rowRange.contains(request.rowNum);
        var seatHasValidRange = seatRange.contains(request.seatNum);
        if ( keyHasText &&  rowHasValidRange && seatHasValidRange) {
            Ticket existingTicket = ticketRepository.findBySeat(1, request.rowNum, request.seatNum);
            if (existingTicket == null) {
                var ticket = new Ticket(1, request.rowNum, request.seatNum, 15.00);
                ticketRepository.saveAndFlush(ticket);
            } else {
                throw new InvalidParameterException("Ticket already exists");
            }
        }
    }
}
