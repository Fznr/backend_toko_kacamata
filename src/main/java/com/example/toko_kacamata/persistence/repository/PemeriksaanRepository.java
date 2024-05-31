package com.example.toko_kacamata.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.toko_kacamata.persistence.entity.Pemeriksaan;

@Repository
public interface PemeriksaanRepository extends JpaRepository<Pemeriksaan, Long> {

    List<Pemeriksaan> findByCustomerId(Long id);

    List<Pemeriksaan> findByTanggalPemeriksaanAndCustomerIdIn(Date date, List<Long> customerIds);
    
}
