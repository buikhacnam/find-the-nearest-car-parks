package com.wego.demo.repository;

import com.wego.demo.entity.CarParkInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarParkInfoRepository extends JpaRepository<CarParkInfoEntity, Long>{
}
