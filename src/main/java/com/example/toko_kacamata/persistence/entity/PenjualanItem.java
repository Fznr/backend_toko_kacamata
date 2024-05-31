package com.example.toko_kacamata.persistence.entity;

import java.math.BigDecimal;

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
@Table(name = "TABEL_PENJUALAN_ITEM")
public class PenjualanItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String itemType;
    private Integer quantity;
    private BigDecimal hargaSatuan;
    private BigDecimal totalHarga;
    private Long itemId;
    private Long penjualanId;
}
