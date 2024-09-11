package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.dto.ChiTietSanPhamDto;
import com.example.kingsneaker.entity.ChiTietSanPham;
import com.example.kingsneaker.mapper.ChiTietSanPhamMapper;
import com.example.kingsneaker.service.ChiTietSanPhamService;
import com.example.kingsneaker.service.SanPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chi-tiet-san-pham")
public class ChiTietSanPhamController {

    @Autowired
    ChiTietSanPhamService service;
    @Autowired
    ChiTietSanPhamMapper chiTietSanPhamMapper;

    @GetMapping("/show-all")
    public ResponseEntity<List<ChiTietSanPham>> showListCTSP(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/new-chi-tiet-san-pham")
    public ResponseEntity<ChiTietSanPham> newCTSP(@RequestBody @Valid ChiTietSanPhamDto ctspDto){
        ChiTietSanPham ctsp =chiTietSanPhamMapper.mapToEntity(ctspDto);
        service.save(ctsp);
        return new ResponseEntity<>(ctsp,HttpStatus.OK);
    }

    @PutMapping("/update-chi-tiet-san-pham/{idCTSP}")
    public ResponseEntity<ChiTietSanPham> updateCTSP(@RequestBody @Valid ChiTietSanPham ctsp,@PathVariable("idCTSP") Long idCTSP){
        ctsp.setId(idCTSP);
        service.save(ctsp);
        return ResponseEntity.ok(ctsp);
    }

    @DeleteMapping("/delete-chi-tiet-san-pham/{idCTSP}")
    public ResponseEntity<ChiTietSanPham> deleteCTSP(@PathVariable("idCTSP") Long idCTSP) {
        ChiTietSanPham ctsp =  service.findById(idCTSP);
        service.deleteById(idCTSP);
        return ResponseEntity.ok(ctsp);
    }

}
