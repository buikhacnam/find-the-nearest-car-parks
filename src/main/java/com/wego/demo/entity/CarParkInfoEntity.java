package com.wego.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car_park_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarParkInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_park_no")
    private String carParkNo;

    @Column(name = "address")
    private String address;

    @Column(name = "x_coord")
    private double xCoord;

    @Column(name = "y_coord")
    private double yCoord;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "car_park_type")
    private String carParkType;

    @Column(name = "type_of_parking_system")
    private String typeOfParkingSystem;

    @Column(name = "short_term_parking")
    private String shortTermParking;

    @Column(name = "free_parking")
    private String freeParking;

    @Column(name = "night_parking")
    private String nightParking;

    @Column(name = "car_park_decks")
    private int carParkDecks;

    @Column(name = "gantry_height")
    private double gantryHeight;

    @Column(name = "car_park_basement")
    private String carParkBasement;

}

