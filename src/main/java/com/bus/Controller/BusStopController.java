package com.bus.Controller;

import com.bus.Entity.BusStop;
import com.bus.Service.BusStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/busstop")
@RestController
public class BusStopController {
    @Autowired
    private BusStopService busStopService;
    @PostMapping()
    public ResponseEntity<BusStop>createbusstop(@RequestBody BusStop busStop) {
        BusStop savedBusStop = busStopService.createbusstop(busStop);
        return new ResponseEntity<>(savedBusStop, HttpStatus.CREATED);
    }
}
