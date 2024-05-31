package com.example.toko_kacamata.controller.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private String nama;
    private String alamat;
    private Date tanggalLahir;
    private String tempatLahir;
}
