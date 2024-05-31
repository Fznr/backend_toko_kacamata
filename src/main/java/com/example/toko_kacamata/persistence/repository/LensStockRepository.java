package com.example.toko_kacamata.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.toko_kacamata.persistence.entity.LensStock;

@Repository
public interface LensStockRepository extends JpaRepository<LensStock, Long> {

    List<LensStock> findBySphereLikeAndCylinderLike(String sphere, String cylinder);

    LensStock findBySphereAndCylinder(String sphere, String cylinder);

}
