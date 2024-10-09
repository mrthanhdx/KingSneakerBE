package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.entity.KieuDang;
import com.example.kingsneaker.service.KieuDangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/admin/api/v1/kieu-dang")

public class KieuDangController {

    @Autowired
    KieuDangService service;

    @GetMapping("/show-all")
    public ResponseEntity<List<KieuDang>> showListKieuDang(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/new-kieu-dang")
    public ResponseEntity<KieuDang> newKieuDang(@RequestBody @Valid KieuDang kg){
        service.save(kg);
        return new ResponseEntity<>(kg,HttpStatus.OK);
    }

    @PutMapping("/update-kieu-dang/{idKieuDang}")
    public ResponseEntity<KieuDang> updateKieuDang(@RequestBody @Valid KieuDang kg,@PathVariable("idKieuDang") Long idKieuDang){
        kg.setId(idKieuDang);
        service.save(kg);
        return ResponseEntity.ok(kg);
    }

    @DeleteMapping("/delete-kieu-dang/{idKieuDang}")
    public ResponseEntity<KieuDang> deleteKieuDang(@PathVariable("idKieuDang") Long idKieuDang) {
        KieuDang kg =  service.findById(idKieuDang);
        service.deleteById(idKieuDang);
        return ResponseEntity.ok(kg);
    }
}
