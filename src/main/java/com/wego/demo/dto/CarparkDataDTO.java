package com.wego.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarparkDataDTO {
    private List<CarparkInfoDTO> carpark_info;
    private String carpark_number;
    private String update_datetime;
}
