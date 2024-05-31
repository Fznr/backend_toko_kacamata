package com.example.toko_kacamata.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.toko_kacamata.persistence.entity.FrameStock;

@Repository
public interface FrameStockRepository extends JpaRepository<FrameStock, Long> {

    FrameStock findByBrandIgnoreCaseAndFrameTypeIgnoreCase(String brand, String frameType);

    List<FrameStock> findByBrandLikeAndFrameTypeLike(String brand, String frameType);
}
