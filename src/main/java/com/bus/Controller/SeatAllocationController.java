package com.bus.Controller;

import com.bus.Entity.SeatAllocation;
import com.bus.Service.SeatAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seatallocate")
public class SeatAllocationController {
    @Autowired
    private SeatAllocationService seatAllocationService;
@PostMapping("/seatallocate")
    public ResponseEntity<SeatAllocation> createSeatAllocation(@RequestBody SeatAllocation seatAllocation){
    SeatAllocation savedallocation=seatAllocationService.createSeatAllocation(seatAllocation);
    return new ResponseEntity<>(savedallocation, HttpStatus.CREATED);
}

}
