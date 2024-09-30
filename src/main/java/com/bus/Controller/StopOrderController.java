package com.bus.Controller;

import com.bus.Entity.StopOrder;
import com.bus.Service.StopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/v1/order")
@RestController
public class StopOrderController {
    @Autowired
    StopOrderService stopOrderService;

    @PostMapping()
    public ResponseEntity<StopOrder> createorder(@RequestBody StopOrder stopOrder,
                                                 @RequestParam  int busId,
                                                 @RequestParam  int busStopId

      ) {


        StopOrder savedOrder = stopOrderService.createorder(stopOrder,busId ,busStopId );
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

}
