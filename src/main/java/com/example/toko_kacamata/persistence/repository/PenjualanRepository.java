package com.example.toko_kacamata.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.toko_kacamata.persistence.entity.Penjualan;

@Repository
public interface PenjualanRepository extends JpaRepository<Penjualan, Long> {
}
