package com.bus.Repository;

import com.bus.Entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusStopRepository extends JpaRepository<BusStop, Integer> {
    @Query("SELECT bs.id FROM BusStop bs WHERE bs.stopName = :stopName")
    Integer findIdByStopName(@Param("stopName") String stopName);
}