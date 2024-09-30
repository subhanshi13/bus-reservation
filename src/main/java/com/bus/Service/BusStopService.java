package com.bus.Service;

import com.bus.Entity.BusStop;
import com.bus.Repository.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusStopService {
    @Autowired
    private BusStopRepository busStopRepository;

    public BusStop createbusstop(BusStop busStop) {
        return busStopRepository.save(busStop);
    }
    public Integer getstopIdbystopName(String stopName){
return busStopRepository.findIdByStopName(stopName);
    }
}
