package com.exercise.cinema.repositories;

import com.exercise.cinema.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, String> {
}
