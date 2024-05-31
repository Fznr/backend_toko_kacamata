package com.example.toko_kacamata.controller.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PemeriksaanDto {
    private String leftSphere;
    private String leftCylinder;
    private String rightSphere;
    private String rightCylinder;
    private Long customerId;
}
