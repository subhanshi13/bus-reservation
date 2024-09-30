package com.bus.Repository;

import com.bus.Entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Integer> {

    @Query("SELECT so.bus, so.price FROM StopOrder so " +
            "JOIN so.busStop stopFrom " +
            "JOIN StopOrder so2 ON so.bus = so2.bus " +
            "JOIN so2.busStop stopTo " +
            "WHERE stopFrom.stopName = :fromLocation " +
            "AND stopTo.stopName = :toLocation " +
            "AND so.departureDate = :departureDate " +
            "AND so.stopOrder < so2.stopOrder")
    List<Object[]> findBusByRoute(@Param("fromLocation") String fromLocation,
                                  @Param("toLocation") String toLocation,
                                  @Param("departureDate") LocalDate departureDate);
}