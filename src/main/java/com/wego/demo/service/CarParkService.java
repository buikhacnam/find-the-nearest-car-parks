package com.wego.demo.service;

import com.wego.demo.dto.CarParkDTO;
import org.springframework.data.domain.Page;


public interface CarParkService {
    Page<CarParkDTO> getNearestCarParks(
            double latitude,
            double longitude,
            int pageNumber,
            int pageSize
    );

}
