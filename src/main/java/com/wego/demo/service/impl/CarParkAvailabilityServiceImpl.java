package com.wego.demo.service.impl;

import com.wego.demo.common.CarParkObserver;
import com.wego.demo.dto.CarParkAvailabilityDTO;
import com.wego.demo.dto.CarparkDataDTO;
import com.wego.demo.service.CarParkAvailabilityService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarParkAvailabilityServiceImpl implements CarParkAvailabilityService {

    private static final String CAR_PARK_API_URL = "https://api.data.gov.sg/v1/transport";

    private final WebClient webClient;

    private List<CarParkObserver> observers = new ArrayList<>();


    public CarParkAvailabilityServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(CAR_PARK_API_URL).build();
    }

    @Override
    public void registerObserver(CarParkObserver observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(CarParkObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(List<CarparkDataDTO> newData) {
        for (CarParkObserver observer : observers) {
            observer.update(newData);
        }
    }

    @Override
    public void updateCarParkAvailability() {
        try {

            List<CarparkDataDTO> carparkDataDTO = this.fetchNewCarParkData();

            if(carparkDataDTO == null) {
                System.out.println("No car park data found");
                return;
            }

            this.notifyObservers(carparkDataDTO);

        } catch (Exception e) {
            System.out.println("Error in updating car park availability");
            e.printStackTrace();
        }
    }

    private List<CarparkDataDTO> fetchNewCarParkData() {
        Mono<CarParkAvailabilityDTO> carParkAvailabilityDTO = webClient.get()
                .uri("/carpark-availability")
                .retrieve()
                .bodyToMono(CarParkAvailabilityDTO.class);

        return carParkAvailabilityDTO.block().getItems().get(0).getCarpark_data();

    }

}
