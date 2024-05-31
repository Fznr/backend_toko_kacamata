package com.example.toko_kacamata.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "TABEL_PENJUALAN")
public class Penjualan {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private Date tanggalPenjualan;
    private BigDecimal totalHarga;
    private Double diskon;
    private Long pemeriksaanId;
    private Long customerId;
}
