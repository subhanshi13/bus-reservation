package com.bus.Controller;

import com.bus.Entity.Seats;
import com.bus.Service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/seats")
@RestController

public class SeatsController {
    @Autowired
    private SeatsService seatsService;
    @PostMapping("/seats")
    public ResponseEntity<Seats> createseats(@RequestBody Seats seats){

        if (seats.getName() == null || seats.getName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Return 400 if name is missing
        }

        Seats savedseats= seatsService.createseats(seats);
        return new ResponseEntity<>(savedseats, HttpStatus.CREATED);
    }
}
