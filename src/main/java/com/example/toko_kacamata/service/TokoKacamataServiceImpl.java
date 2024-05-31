package com.example.toko_kacamata.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.toko_kacamata.controller.entity.CustomerDto;
import com.example.toko_kacamata.controller.entity.FrameStockDto;
import com.example.toko_kacamata.controller.entity.LensStockDto;
import com.example.toko_kacamata.controller.entity.PemeriksaanDto;
import com.example.toko_kacamata.controller.entity.PenjualanItemDto;
import com.example.toko_kacamata.controller.entity.PenjualanRequestDto;
import com.example.toko_kacamata.persistence.entity.Customer;
import com.example.toko_kacamata.persistence.entity.FrameStock;
import com.example.toko_kacamata.persistence.entity.LensStock;
import com.example.toko_kacamata.persistence.entity.Pemeriksaan;
import com.example.toko_kacamata.persistence.entity.Penjualan;
import com.example.toko_kacamata.persistence.entity.PenjualanItem;
import com.example.toko_kacamata.persistence.repository.CustomerRepository;
import com.example.toko_kacamata.persistence.repository.FrameStockRepository;
import com.example.toko_kacamata.persistence.repository.LensStockRepository;
import com.example.toko_kacamata.persistence.repository.PemeriksaanRepository;
import com.example.toko_kacamata.persistence.repository.PenjualanItemRepository;
import com.example.toko_kacamata.persistence.repository.PenjualanRepository;

@Service
public class TokoKacamataServiceImpl implements TokoKacamataService {

    @Autowired
    FrameStockRepository frameStockRepository;

    @Autowired
    LensStockRepository lensStockRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PemeriksaanRepository pemeriksaanRepository;

    @Autowired
    PenjualanRepository penjualanRepository;

    @Autowired
    PenjualanItemRepository penjualanItemRepository;

    @Override
    public FrameStock createNewFrameStock(FrameStockDto request) {
        FrameStock frameStock = this.convertToFrameStock(request);
        FrameStock existingFrameStock = this.frameStockRepository
                .findByBrandIgnoreCaseAndFrameTypeIgnoreCase(request.getBrand(), request.getFrameType());
        if (existingFrameStock != null) {
            String error = "Data already exist, " + HttpStatus.CONFLICT;
            throw new Error(error);
        }
        return this.frameStockRepository.save(frameStock);
    }

    @Override
    public List<FrameStock> editFrameStock(List<FrameStock> requests) {
        return this.frameStockRepository.saveAll(requests);
    }

    @Override
    public List<FrameStock> findFrames(String brand, String frameType) {
        String brandConvert = "%" + brand + "%";
        String frameTypeConvert = "%" + frameType + "%";
        return this.frameStockRepository.findByBrandLikeAndFrameTypeLike(brandConvert, frameTypeConvert);
    }

    private FrameStock convertToFrameStock(FrameStockDto frameStockDto) {
        FrameStock frameStock = new FrameStock();
        frameStock.setBrand(frameStockDto.getBrand());
        frameStock.setFrameType(frameStockDto.getFrameType());
        frameStock.setPrice(frameStockDto.getPrice());
        frameStock.setStock(frameStockDto.getStock());
        return frameStock;
    }

    @Override
    public LensStock createNewLensStock(LensStockDto request) {
        LensStock lensStock = this.convertToLensStock(request);
        LensStock existingLensStock = this.lensStockRepository.findBySphereAndCylinder(request.getSphere(),
                request.getCylinder());
        if (existingLensStock != null) {
            String error = "Data already exist, " + HttpStatus.CONFLICT;
            throw new Error(error);
        }
        return this.lensStockRepository.save(lensStock);
    }

    @Override
    public List<LensStock> editLensStock(List<LensStock> requests) {
        return this.lensStockRepository.saveAll(requests);
    }

    @Override
    public List<LensStock> findLens(String sphere, String cylinder) {
        String sphereConvert = "%" + sphere + "%";
        String cylinderConvert = "%" + cylinder + "%";

        return this.lensStockRepository.findBySphereLikeAndCylinderLike(sphereConvert, cylinderConvert);
    }

    private LensStock convertToLensStock(LensStockDto request) {
        LensStock lensStock = new LensStock();
        lensStock.setSphere(request.getSphere());
        lensStock.setCylinder(request.getCylinder());
        lensStock.setPrice(request.getPrice());
        lensStock.setStock(request.getStock());
        return lensStock;
    }

    @Override
    public Customer addCustomer(CustomerDto request) {
        Customer customer = Customer.builder().alamat(request.getAlamat()).nama(request.getNama())
                .tanggalLahir(request.getTanggalLahir()).tempatLahir(request.getTempatLahir()).build();
        return this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> findCustomers(String nama, String alamat) {
        return this.customerRepository.findByNamaContainingIgnoreCaseOrAlamatContainingIgnoreCase(nama, alamat);
    }

    @Override
    public Pemeriksaan createPemeriksaan(PemeriksaanDto request) {
        Pemeriksaan pemeriksaan = Pemeriksaan.builder().customerId(request.getCustomerId())
                .leftCylinder(request.getLeftCylinder()).leftSphere(request.getLeftSphere())
                .rightSphere(request.getRightSphere()).rightCylinder(request.getRightCylinder())
                .tanggalPemeriksaan(new Date()).build();
        return this.pemeriksaanRepository.save(pemeriksaan);
    }

    @Override
    public List<Pemeriksaan> findPemeriksaanByCustomerId(Long customerId) {
        return this.pemeriksaanRepository.findByCustomerId(customerId);
    }

    @Override
    public Long addPenjualan(PenjualanRequestDto request) {
        Penjualan penjualan = new Penjualan();
        penjualan.setTanggalPenjualan(new Date());
        penjualan.setTotalHarga(request.getPenjualanDto().getTotalHarga());
        penjualan.setDiskon(request.getPenjualanDto().getDiskon());
        penjualan.setPemeriksaanId(request.getPenjualanDto().getPemeriksaanId());
        penjualan.setCustomerId(request.getPenjualanDto().getCustomerId());
        Penjualan penjualanSaved = this.penjualanRepository.save(penjualan);

        for (PenjualanItemDto item : request.getPenjualanItemDtos()) {
            if (item.getItemType().equalsIgnoreCase("frame")) {
                FrameStock frameStock = this.frameStockRepository.findById(item.getItemId()).orElse(null);
                Integer newStock = frameStock.getStock() - item.getQuantity();
                frameStock.setStock(newStock);
                this.frameStockRepository.save(frameStock);
            } else if (item.getItemType().equalsIgnoreCase("lens")) {
                LensStock lensStock = this.lensStockRepository.findById(item.getItemId()).orElse(null);
                Integer newStock = lensStock.getStock() - item.getQuantity();
                lensStock.setStock(newStock);
                this.lensStockRepository.save(lensStock);
            }
            PenjualanItem penjualanItem = new PenjualanItem();
            penjualanItem.setItemType(item.getItemType());
            penjualanItem.setQuantity(item.getQuantity());
            penjualanItem.setHargaSatuan(item.getHargaSatuan());
            penjualanItem.setTotalHarga(item.getTotalHarga());
            penjualanItem.setItemId(item.getItemId());
            penjualanItem.setPenjualanId(penjualanSaved.getId());
            this.penjualanItemRepository.save(penjualanItem);
        }
        return penjualanSaved.getId();
    }

    public List<Pemeriksaan> findPemeriksaan(String nama, String date) throws ParseException {
        List<Long> customerIds = this.customerRepository.findIdsByNama(nama);
        System.out.println(date);
        Date formattedDate = new Date();
        if (!date.equalsIgnoreCase("")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formattedDate = formatter.parse(date);
        }
        System.out.println(formattedDate);

        List<Pemeriksaan> datas = this.pemeriksaanRepository.findByTanggalPemeriksaanAndCustomerIdIn(formattedDate,
                customerIds);
        System.out.println(datas);
        return datas;
    }
}
