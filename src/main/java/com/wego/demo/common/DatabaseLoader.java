package com.wego.demo.common;

import com.wego.demo.entity.CarParkInfoEntity;
import com.wego.demo.repository.CarParkInfoRepository;
import com.wego.demo.service.CarParkAvailabilityService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.Reader;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private CarParkInfoRepository carParkInfoRepository;

    @Autowired
    private CarParkAvailabilityService carParkAvailabilityService;

    @Override
    public void run(String... args) throws Exception {
        CoordinateConverter converter = new CoordinateConverter();

        if (carParkInfoRepository.count() == 0) {

            try (Reader in = new InputStreamReader(new ClassPathResource("HDBCarparkInformation.csv").getInputStream())) {
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
                for (CSVRecord record : records) {
                    Coordinate coord = converter.transform(
                            Double.parseDouble(record.get("x_coord")),
                            Double.parseDouble(record.get("y_coord"))
                    );
                    CarParkInfoEntity entity = new CarParkInfoEntity(
                            null,
                            record.get("car_park_no"),
                            record.get("address"),
                            Double.parseDouble(record.get("x_coord")),
                            Double.parseDouble(record.get("y_coord")),
                            coord.getX(),
                            coord.getY(),
                            record.get("car_park_type"),
                            record.get("type_of_parking_system"),
                            record.get("short_term_parking"),
                            record.get("free_parking"),
                            record.get("night_parking"),
                            Integer.parseInt(record.get("car_park_decks")),
                            Double.parseDouble(record.get("gantry_height")),
                            record.get("car_park_basement")
                    );
                    carParkInfoRepository.save(entity);
                }
            }
        } else {
            System.out.println("Carpark info already populated");
        }

        carParkAvailabilityService.updateCarParkAvailability();

    }
}
