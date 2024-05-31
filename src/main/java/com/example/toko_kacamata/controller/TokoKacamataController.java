package com.example.toko_kacamata.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.toko_kacamata.controller.entity.CustomerDto;
import com.example.toko_kacamata.controller.entity.FrameStockDto;
import com.example.toko_kacamata.controller.entity.LensStockDto;
import com.example.toko_kacamata.controller.entity.PemeriksaanDto;
import com.example.toko_kacamata.controller.entity.PenjualanRequestDto;
import com.example.toko_kacamata.persistence.entity.Customer;
import com.example.toko_kacamata.persistence.entity.FrameStock;
import com.example.toko_kacamata.persistence.entity.LensStock;
import com.example.toko_kacamata.persistence.entity.Pemeriksaan;
import com.example.toko_kacamata.service.TokoKacamataService;

@EnableWebMvc
@Controller
@RestController
@RequestMapping(path = "/kacamata")
@CrossOrigin(origins = "http://localhost:4200")
public class TokoKacamataController {

    @Autowired
    TokoKacamataService tokoKacamataService;

    @PostMapping(path = "/add-frame-stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FrameStock> addFrameStock(@RequestBody FrameStockDto request) {
        FrameStock frameStock = this.tokoKacamataService.createNewFrameStock(request);
        return new ResponseEntity<>(frameStock, HttpStatus.CREATED);
    }

    @PostMapping(path = "/edit-frame-stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FrameStock>> editFrameStock(@RequestBody List<FrameStock> requests) {
        List<FrameStock> frameStocks = this.tokoKacamataService.editFrameStock(requests);
        return new ResponseEntity<>(frameStocks, HttpStatus.OK);
    }

    @GetMapping(path = "/find-frames")
    public ResponseEntity<List<FrameStock>> findFrames(@RequestParam(defaultValue = "") String brand,
    @RequestParam(defaultValue = "") String frameType) {
        List<FrameStock> frameStocks = this.tokoKacamataService.findFrames(brand, frameType);
        return new ResponseEntity<>(frameStocks, HttpStatus.OK);
    }

    @GetMapping(path = "/find-lens")
    public ResponseEntity<List<LensStock>> findLens(@RequestParam(defaultValue = "") String sphere,
    @RequestParam(defaultValue = "") String cylinder) {
        List<LensStock> lensStocks = this.tokoKacamataService.findLens(sphere, cylinder);
        return new ResponseEntity<>(lensStocks, HttpStatus.OK);
    }

    @PostMapping(path = "/add-lens-stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LensStock> addLensStock(@RequestBody LensStockDto request) {
        LensStock lensStock = this.tokoKacamataService.createNewLensStock(request);
        return new ResponseEntity<>(lensStock, HttpStatus.CREATED);
    }

    @PostMapping(path = "/edit-lens-stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LensStock>> editLensStock(@RequestBody List<LensStock> requests) {
        List<LensStock> lensStocks = this.tokoKacamataService.editLensStock(requests);
        return new ResponseEntity<>(lensStocks, HttpStatus.OK);
    }

    @PostMapping(path = "/add-customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDto request) {
        Customer customer = this.tokoKacamataService.addCustomer(request);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping(path = "/find-customers")
    public ResponseEntity<List<Customer>> findCustomers(@RequestParam(defaultValue = "") String nama,
            @RequestParam(defaultValue = "") String alamat) {
        List<Customer> customers = this.tokoKacamataService.findCustomers(nama, alamat);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping(path = "/add-pemeriksaan", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pemeriksaan> addPemeriksaan(@RequestBody PemeriksaanDto request) {
        Pemeriksaan pemeriksaan = this.tokoKacamataService.createPemeriksaan(request);
        return new ResponseEntity<>(pemeriksaan, HttpStatus.CREATED);
    }

    @GetMapping(path = "/find-pemeriksaan")
    public ResponseEntity<List<Pemeriksaan>> findPemeriksaan(@RequestParam(defaultValue = "") String nama,
            @RequestParam(defaultValue = "") String tanggalPemeriksaan) throws ParseException {
        List<Pemeriksaan> pemeriksaanDatas = this.tokoKacamataService.findPemeriksaan(nama, tanggalPemeriksaan);
        return new ResponseEntity<>(pemeriksaanDatas, HttpStatus.OK);
    }

    @PostMapping(path = "/add-penjualan", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addPenjualan(@RequestBody PenjualanRequestDto request) {
        Long pemeriksaanId = this.tokoKacamataService.addPenjualan(request);
        return new ResponseEntity<>(pemeriksaanId, HttpStatus.CREATED);
    }
}
