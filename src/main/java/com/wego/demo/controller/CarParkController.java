package com.wego.demo.controller;


import com.wego.demo.dto.CarParkDTO;
import com.wego.demo.payload.response.CommonResponse;
import com.wego.demo.service.CarParkAvailabilityService;
import com.wego.demo.service.CarParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carparks")
public class CarParkController {

    @Autowired
    private CarParkAvailabilityService carParkAvailabilityService;

    @Autowired
    private CarParkService carParkService;

    @GetMapping("/nearest")
    public ResponseEntity<?> getNearestCarParks(
            @RequestParam @NotNull double latitude,
            @RequestParam @NotNull double longitude,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(name = "per_page", defaultValue = "3") int perPage) {

        Page<CarParkDTO> carParks = carParkService.getNearestCarParks(latitude, longitude, page, perPage);
        return ResponseEntity.ok(carParks);
    }

    @GetMapping("/update-availability")
    public ResponseEntity<CommonResponse> updateAvailability() {
        carParkAvailabilityService.updateCarParkAvailability();
        return new ResponseEntity<>(new CommonResponse("OK",  HttpStatus.OK.value()), HttpStatus.OK);
    }
}
