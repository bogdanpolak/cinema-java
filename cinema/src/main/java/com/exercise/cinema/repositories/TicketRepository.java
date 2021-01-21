package com.exercise.cinema.repositories;

import com.exercise.cinema.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query("SELECT t FROM tickets t WHERE t.showId = ?1 and t.rowNum = ?2 and t.seatNum = ?3")
    Ticket findBySeat(String showId, int rowNum, int seatNum);
}
