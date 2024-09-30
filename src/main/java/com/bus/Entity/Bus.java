package com.bus.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//The GenerationType.IDENTITY strategy relies on the auto-increment feature of the database to generate unique primary key values.
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "bus_number", nullable = false)
    private Integer busNumber;

    @Column(name = "base_price", nullable = false) // Add this line
    private Integer basePrice; // New field for base price

}