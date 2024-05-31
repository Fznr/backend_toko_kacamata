package com.example.toko_kacamata.controller.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PenjualanDto {
    private BigDecimal totalHarga;
    private Double diskon;
    private Long pemeriksaanId;
    private Long customerId;
}
