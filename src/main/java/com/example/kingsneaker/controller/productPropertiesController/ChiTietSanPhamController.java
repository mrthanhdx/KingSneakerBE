package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.request.ChiTietSanPhamRequest;
import com.example.kingsneaker.response.ChiTietSanPhamResponse;
import com.example.kingsneaker.entity.ChiTietSanPham;
import com.example.kingsneaker.mapper.ChiTietSanPhamMapper;
import com.example.kingsneaker.service.ChiTietSanPhamService;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/chi-tiet-san-pham")
@CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class ChiTietSanPhamController {

    @Autowired
    ChiTietSanPhamService service;
    @Autowired
    ChiTietSanPhamMapper chiTietSanPhamMapper;

    @GetMapping("/show-all")
    public ResponseEntity<List<ChiTietSanPhamResponse>> showListCTSP() {
        List<ChiTietSanPham> listCtsp = service.findAll();
        List<ChiTietSanPhamResponse> listCtspDto = new ArrayList<>();
        for (ChiTietSanPham ctsp : listCtsp) {
            listCtspDto.add(chiTietSanPhamMapper.mapToResponse(ctsp));
        }
        return new ResponseEntity<>(listCtspDto, HttpStatus.OK);
    }

    @PostMapping("/new-chi-tiet-san-pham")
    public ResponseEntity<ChiTietSanPhamResponse> newCTSP(@RequestBody @Valid ChiTietSanPhamRequest ctspRequest) {
        try {
            ChiTietSanPham ctsp = chiTietSanPhamMapper.mapToEntity(ctspRequest);
            service.save(ctsp);
            ChiTietSanPhamResponse response = chiTietSanPhamMapper.mapToResponse(ctsp);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Add Product Detail failed !");
        }
    }

    @PutMapping("/update-chi-tiet-san-pham/{idCTSP}")
    public ResponseEntity<ChiTietSanPhamResponse> updateCTSP(@RequestBody @Valid ChiTietSanPhamRequest ctspRequest, @PathVariable("idCTSP") Long idCTSP) {
        try {
            ctspRequest.setId(idCTSP);
            ChiTietSanPham ctsp = chiTietSanPhamMapper.mapToEntity(ctspRequest);
            ChiTietSanPhamResponse response = chiTietSanPhamMapper.mapToResponse(ctsp);
            service.save(ctsp);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Update Product Detail failed !");
        }
    }

//    @DeleteMapping("/delete-chi-tiet-san-pham/{idCTSP}")
//    public ResponseEntity<ChiTietSanPham> deleteCTSP(@PathVariable("idCTSP") Long idCTSP) {
//        ChiTietSanPham ctsp = service.findById(idCTSP);
//        service.deleteById(idCTSP);
//        return ResponseEntity.ok(ctsp);
//    }

}
