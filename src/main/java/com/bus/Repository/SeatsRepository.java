package com.bus.Repository;

import com.bus.Entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatsRepository extends JpaRepository<Seats, Integer> {
}