package com.exercise.cinema.repositories;

import com.exercise.cinema.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM tickets t WHERE t.RoomId = ?1 and t.RowNum = ?2 and t.SeatNum = ?3")
    Ticket findBySeat(int RoomId, int RowNum, int SeatNum);
}
