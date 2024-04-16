package com.wego.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarParkDTO {

    private String address;

    private double latitude;

    private double longitude;

    private int totalLots;

    private int availableLots;

    private double kmDistance;
}
