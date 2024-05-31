package com.example.toko_kacamata.controller.entity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FrameStockDto {
    private String brand;
    private String frameType;
    private BigDecimal price; 
    private Integer Stock; 
}
