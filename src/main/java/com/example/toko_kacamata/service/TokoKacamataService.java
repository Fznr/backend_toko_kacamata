package com.example.toko_kacamata.service;

import java.text.ParseException;
import java.util.List;

import com.example.toko_kacamata.controller.entity.CustomerDto;
import com.example.toko_kacamata.controller.entity.FrameStockDto;
import com.example.toko_kacamata.controller.entity.LensStockDto;
import com.example.toko_kacamata.controller.entity.PemeriksaanDto;
import com.example.toko_kacamata.controller.entity.PenjualanRequestDto;
import com.example.toko_kacamata.persistence.entity.Customer;
import com.example.toko_kacamata.persistence.entity.FrameStock;
import com.example.toko_kacamata.persistence.entity.LensStock;
import com.example.toko_kacamata.persistence.entity.Pemeriksaan;

public interface TokoKacamataService {

    public FrameStock createNewFrameStock(FrameStockDto request);
    public List<FrameStock> editFrameStock(List<FrameStock> requests);
    public List<FrameStock> findFrames(String brand, String frameType);
    public LensStock createNewLensStock(LensStockDto request);
    public List<LensStock> editLensStock(List<LensStock> requests);
    public List<LensStock> findLens(String sphere, String cylinder);
    public Customer addCustomer (CustomerDto request);
    public List<Customer> findCustomers(String nama, String alamat);
    public Pemeriksaan createPemeriksaan(PemeriksaanDto request);
    public List<Pemeriksaan> findPemeriksaanByCustomerId(Long customerId);
    public Long addPenjualan(PenjualanRequestDto request);
    public List<Pemeriksaan> findPemeriksaan(String nama, String date) throws ParseException;
}
