package com.exercise.cinema.repositories;

import com.exercise.cinema.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
