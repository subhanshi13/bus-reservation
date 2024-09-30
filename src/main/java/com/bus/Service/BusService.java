package com.bus.Service;

import com.bus.Entity.Bus;
import com.bus.Repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    public Bus createBus(Bus bus){
        return busRepository.save(bus);
    }
    public List<Object[]> findBusByRoute(String fromLocation, String  toLocation, LocalDate departureDate){
        return busRepository.findBusByRoute(fromLocation,toLocation, departureDate);
    }
}
