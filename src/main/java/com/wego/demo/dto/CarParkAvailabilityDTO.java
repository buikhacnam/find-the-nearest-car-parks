package com.wego.demo.dto;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarParkAvailabilityDTO {
    private List<ItemDTO> items;
    private String timestamp;
}

