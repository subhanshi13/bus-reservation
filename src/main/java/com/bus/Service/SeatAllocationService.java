package com.bus.Service;


import com.bus.Entity.SeatAllocation;
import com.bus.Repository.SeatAllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatAllocationService {
    @Autowired
    private SeatAllocationRepository seatAllocationRepository;

    public SeatAllocation createSeatAllocation(SeatAllocation seatAllocation) {
        return seatAllocationRepository.save(seatAllocation);
    }

    public List<String> getSeatAllocations(int startStation, int endStation) {
        return seatAllocationRepository.findSeatNamesByStartAndEndStation(startStation, endStation);
    }
        public List<String> getSeatAllocationsForSub( int startStation, int endStation ){
            return seatAllocationRepository.findSeatNamesBySubstationOrder(startStation, endStation);



    }
}
