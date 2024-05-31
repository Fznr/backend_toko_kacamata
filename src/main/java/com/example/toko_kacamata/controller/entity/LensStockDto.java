package com.example.toko_kacamata.controller.entity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LensStockDto {
    private String sphere;
    private String cylinder;
    private BigDecimal price; 
    private Integer Stock; 
}
