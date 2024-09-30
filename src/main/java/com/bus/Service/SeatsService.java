package com.bus.Service;

import com.bus.Entity.Seats;
import com.bus.Repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatsService {
    @Autowired
    private SeatsRepository seatsRepository;

    public Seats createseats(Seats seats){
        return seatsRepository.save(seats);
    }
}
