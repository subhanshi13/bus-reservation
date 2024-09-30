package com.bus.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seat_allocation")
public class SeatAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "start_station", nullable = false)
    private int startStation;

    @Column(name = "end_station", nullable = false)
    private int endStation;

    @Column(name = "seat_name", nullable = false)
    private String seatName;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

}