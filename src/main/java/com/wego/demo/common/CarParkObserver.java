package com.wego.demo.common;

import com.wego.demo.dto.CarparkDataDTO;

import java.util.List;

public interface CarParkObserver {
    void update(List<CarparkDataDTO> newData);
}
