package com.bus.Repository;

import com.bus.Entity.SeatAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatAllocationRepository extends JpaRepository<SeatAllocation, Integer> {

    @Query("SELECT sa.seatName FROM SeatAllocation sa WHERE sa.startStation >= :startStation AND sa.endStation <= :endStation")
 List<String> findSeatNamesByStartAndEndStation(@Param("startStation") int startStation, @Param("endStation") int endStation);

    @Query("SELECT sa.seatName FROM SeatAllocation sa WHERE sa.startStation >= :startStation AND sa.endStation <= :endStation")
    List<String> findSeatNamesBySubstationOrder(@Param("startStation") int startStation, @Param("endStation") int endStation);
}