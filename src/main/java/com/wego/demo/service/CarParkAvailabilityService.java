package com.wego.demo.service;

import com.wego.demo.common.CarParkObserver;
import com.wego.demo.entity.CarParkEntity;

import java.util.List;

public interface CarParkAvailabilityService {
    void updateCarParkAvailability();
    void registerObserver(CarParkObserver observer);
    void removeObserver(CarParkObserver observer);

}
