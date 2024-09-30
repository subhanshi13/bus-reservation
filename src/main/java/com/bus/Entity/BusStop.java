package com.bus.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bus_stop")
public class BusStop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "stop_name", nullable = false)
    private String stopName;

    @Column(name = "distance_factor", nullable = false) // Use snake_case for consistency
    private Integer distanceFactor; // New field for distance factor

}