package com.bus.Service;

import com.bus.Entity.Bus;
import com.bus.Entity.BusStop;
import com.bus.Entity.StopOrder;
import com.bus.Repository.BusRepository;
import com.bus.Repository.BusStopRepository;
import com.bus.Repository.StopOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StopOrderService {
    @Autowired
    private StopOrderRepository stopOrderRepository;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private BusStopRepository busStopRepository;

    public StopOrder createorder(StopOrder stopOrder, int busId,  int busStopId) {

        Optional<Bus> byId=busRepository.findById(busId);
        if (byId.isEmpty()) {
            throw new IllegalArgumentException("Bus not found with ID: " + busId);
        }
        Bus bus = byId.get();

        Optional<BusStop> bycountry=busStopRepository.findById(busStopId);
        if (bycountry.isEmpty()) {
            throw new IllegalArgumentException("Bus stop not found with ID: " + busStopId);
        }
       BusStop busStop= bycountry.get();

        // Validate the departure date (must be in the future)
        if (stopOrder.getDepartureDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Departure date must be in the future.");
        }
// Calculate and set the price (assumed that price is on Bus or BusStop)
      Integer   price = calculatePrice(bus, busStop); // Define this method to calculate price based on your logic
        stopOrder.setPrice(price);

        stopOrder.setBus(bus);
        stopOrder.setBusStop(busStop);
        // Save and return the StopOrder
        return stopOrderRepository.save(stopOrder);

    }

    private Integer calculatePrice(Bus bus, BusStop busStop) {
        // Implement your pricing logic here. For example:
        Integer basePrice = bus.getBasePrice(); // Example field on Bus
        Integer distanceFactor = busStop.getDistanceFactor(); // Example field on BusStop
        return basePrice + distanceFactor; // Example calculation
    }
    public Integer getStopOrdersByBusIdAndBusStopId(Integer busId, Integer busStopId) {
        return stopOrderRepository.findStopOrderByBusIdAndBusStopId(busId, busStopId);
    }


}



