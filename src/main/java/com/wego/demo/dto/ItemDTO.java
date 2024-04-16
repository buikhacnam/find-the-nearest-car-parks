package com.wego.demo.dto;

import jakarta.persistence.MapKey;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemDTO {
    private String timestamp;
    private List<CarparkDataDTO> carpark_data;
}
