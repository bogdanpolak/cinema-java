package com.exercise.cinema.controllers;

import com.exercise.cinema.models.Ticket;
import com.exercise.cinema.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
