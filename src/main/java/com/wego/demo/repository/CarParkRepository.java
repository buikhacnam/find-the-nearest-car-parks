package com.wego.demo.repository;

import com.wego.demo.dto.CarParkDTO;
import com.wego.demo.entity.CarParkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface CarParkRepository extends JpaRepository<CarParkEntity, String> {

    @Query(
            value = "SELECT new com.wego.demo.dto.CarParkDTO(" +
                    "car_park.address, " +
                    "car_park.latitude, " +
                    "car_park.longitude, " +
                    "car_park.totalLots, " +
                    "car_park.availableLots, " +
                    "(6371 * acos(" +
                    "cos(radians(?1)) * " +
                    "cos(radians(car_park.latitude)) * " +
                    "cos(radians(car_park.longitude) - radians(?2)) + " +
                    "sin(radians(?1)) * sin(radians(car_park.latitude))" +
                    ")) AS distance) " +
                    "FROM CarParkEntity car_park " +
                    "WHERE car_park.availableLots > 0 " +
                    "ORDER BY distance",
            countQuery = "SELECT count(*) FROM CarParkEntity where availableLots > 0",
            nativeQuery = false
    )
    Page<CarParkDTO> findNearestCarParks(double latitude, double longitude, Pageable pageable);
}
