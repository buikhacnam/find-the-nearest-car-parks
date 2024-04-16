package com.wego.demo.service.impl;

import com.wego.demo.common.CarParkObserver;
import com.wego.demo.dto.CarParkDTO;
import com.wego.demo.dto.CarparkDataDTO;
import com.wego.demo.entity.CarParkEntity;
import com.wego.demo.entity.CarParkInfoEntity;
import com.wego.demo.repository.CarParkInfoRepository;
import com.wego.demo.repository.CarParkRepository;
import com.wego.demo.service.CarParkAvailabilityService;
import com.wego.demo.service.CarParkService;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarParkServiceImpl implements CarParkService, CarParkObserver {
    @Autowired
    private CarParkRepository carParkRepository;

    @Autowired
    private CarParkAvailabilityService carParkAvailabilityService;

    @Autowired
    private CarParkInfoRepository carParkInfoRepository;

    @PostConstruct
    public void init() {
        carParkAvailabilityService.registerObserver(this);
    }

    public Page<CarParkDTO> getNearestCarParks(
            double latitude,
            double longitude,
            int pageNumber,
            int pageSize) {

        if (pageNumber < 1) {
            pageNumber = 1;
        }

        if (pageSize < 1) {
            pageSize = 1;
        }

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return carParkRepository.findNearestCarParks(latitude, longitude, pageRequest);
    }

    private Map<String, CarParkInfoEntity> generateCarParkInfoMap() {
        List<CarParkInfoEntity> carParkInfoEntities = carParkInfoRepository.findAll();

        Map<String, CarParkInfoEntity> carParkInfoObj = carParkInfoEntities.stream()
                .collect(
                        HashMap::new,
                        (map, carParkInfoEntity) -> map.put(carParkInfoEntity.getCarParkNo(), carParkInfoEntity),
                        Map::putAll
                );

        return carParkInfoObj;
    }

    @Override
    public void update(List<CarparkDataDTO> newData) {
        if(newData == null || newData.isEmpty()) {
            return;
        }
        Map<String, CarParkInfoEntity> carParkInfoMap = generateCarParkInfoMap();

        List<CarParkEntity> savedData = new ArrayList<>();

        for (CarparkDataDTO data : newData) {
            CarParkInfoEntity carParkInfoEntity = carParkInfoMap.get(data.getCarpark_number());
            if (carParkInfoEntity != null) {
                CarParkEntity carParkEntity = new CarParkEntity();
                carParkEntity.setCarParkNo(carParkInfoEntity.getCarParkNo());
                carParkEntity.setAddress(carParkInfoEntity.getAddress());
                carParkEntity.setLatitude(carParkInfoEntity.getLatitude());
                carParkEntity.setLongitude(carParkInfoEntity.getLongitude());
                carParkEntity.setTotalLots(Integer.parseInt(data.getCarpark_info().get(0).getTotal_lots()));
                carParkEntity.setAvailableLots(Integer.parseInt(data.getCarpark_info().get(0).getLots_available()));
                savedData.add(carParkEntity);
            }
        }
        carParkRepository.saveAll(savedData);
    }
}
