package com.example.toko_kacamata.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.toko_kacamata.persistence.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByNamaContainingIgnoreCaseOrAlamatContainingIgnoreCase(String nama, String alamat);

    @Query("SELECT c.id FROM Customer c WHERE LOWER(c.nama) = LOWER(:nama)")
    List<Long> findIdsByNama(@Param("nama") String nama);

}
