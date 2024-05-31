package com.example.toko_kacamata.controller.entity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PenjualanItemDto {
    private String itemType;
    private Integer quantity;
    private BigDecimal hargaSatuan;
    private BigDecimal totalHarga;
    private Long itemId;
}
