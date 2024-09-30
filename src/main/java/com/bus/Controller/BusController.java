package com.bus.Controller;

import com.bus.Entity.Bus;
import com.bus.Entity.StopOrder;
import com.bus.Service.BusService;
import com.bus.Service.BusStopService;
import com.bus.Service.SeatAllocationService;
import com.bus.Service.StopOrderService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/bus")
public class BusController {
    private static final Logger logger = LoggerFactory.getLogger(BusController.class);
    @Autowired
    private BusService busService;

    @Autowired
    private BusStopService busStopService;

    @Autowired
    private StopOrderService stopOrderService;

    @Autowired
    private SeatAllocationService seatAllocationService;

    @PostMapping()
    // Create a new bus
    public ResponseEntity<Bus> createBus(@RequestBody Bus bus) {
        Bus savedbus = busService.createBus(bus);
        return new ResponseEntity<>(savedbus, HttpStatus.CREATED);

    }

    @GetMapping("/buslist")
    public ResponseEntity<List<Object[]>> searchBuses(
            @RequestParam String fromLocation,
            @RequestParam String toLocation,
            @RequestParam(required = false) String departureDate) {

        if (fromLocation.isEmpty() || toLocation.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        List<Object[]> buses;
        if (departureDate == null || departureDate.trim().isEmpty()) {
            // Return all buses for the specified route if no departure date is given
            buses = busService.findBusByRoute(fromLocation, toLocation, null);
        } else {
            try {
                String[] travelDate = departureDate.split("-");
                LocalDate date = LocalDate.of(
                        Integer.parseInt(travelDate[0].trim()),
                        Integer.parseInt(travelDate[1].trim()),
                        Integer.parseInt(travelDate[2].trim())
                );
                buses = busService.findBusByRoute(fromLocation, toLocation, date);
            } catch (Exception e) {
                logger.error("Invalid date format", e);
                return ResponseEntity.badRequest().body(Collections.emptyList());
            }
        }

        // Prepare the response
        List<Object[]> busesWithPrices = new ArrayList<>(buses);

        if (busesWithPrices.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(busesWithPrices);
        }

        return ResponseEntity.ok(busesWithPrices);
    }
    @GetMapping("/seatallocation")
    public List<String> getStopOrdersByBusIdAndBusStopId(
            @RequestParam("busId") Integer busId,
            @RequestParam("busStopId") Integer busStopId,
            @RequestParam String startStationName,
            @RequestParam String lastStationName) {
        // Get the starting station ID based on busId and busStopId
        Integer startstationId = stopOrderService.getStopOrdersByBusIdAndBusStopId(busId, busStopId);
        // Get the stop ID of the last station using the station name
        Integer laststopId = busStopService.getstopIdbystopName(lastStationName);

        Integer startstopId = busStopService.getstopIdbystopName(startStationName);

        // Get the last station ID based on the busId and the stop ID of the last station
        Integer laststationId = stopOrderService.getStopOrdersByBusIdAndBusStopId(busId, laststopId);

        Integer substationId = stopOrderService.getStopOrdersByBusIdAndBusStopId(busId, startstopId);

        if (startstationId == null || laststationId == null || startstationId > laststationId) {
            throw new IllegalArgumentException("Invalid station range.");
        }

        List<String> seatsBooked = new ArrayList<>();
        List<String> seats = seatAllocationService.getSeatAllocations(startstationId, laststationId);
        seatsBooked.addAll(seats);


        if (!startstationId.equals(substationId)) {
           // for (int i = startstationId; i > 0; i--) {
                List<String> subSeats = seatAllocationService.getSeatAllocationsForSub(substationId, laststationId);
                if (!subSeats.isEmpty()) {
                String joinedSeats = String.join(", ", subSeats);
                System.out.println("Joined seats for substation: " + joinedSeats);
                seatsBooked.addAll(subSeats);
            }


        }
   // }
        return seatsBooked;
    }

    }

