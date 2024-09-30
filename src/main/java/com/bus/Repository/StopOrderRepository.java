package com.bus.Repository;

import com.bus.Entity.StopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StopOrderRepository extends JpaRepository<StopOrder, Integer> {

    @Query("SELECT so.stopOrder FROM StopOrder so WHERE so.bus.id = :busId AND so.busStop.id = :busStopId")
    Integer findStopOrderByBusIdAndBusStopId(@Param("busId") Integer busId, @Param("busStopId") Integer busStopId);

}