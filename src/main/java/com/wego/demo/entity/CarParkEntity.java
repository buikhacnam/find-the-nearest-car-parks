package com.wego.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "car_park")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarParkEntity {

    @Id
    @Column(name = "car_park_no")
    private String carParkNo;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "total_lots")
    private int totalLots;

    @Column(name = "available_lots")
    private int availableLots;


    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Transient
    @Column(name = "distance")
    private double kmDistance;
}
