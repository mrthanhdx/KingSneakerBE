package com.example.kingsneaker.controller.nonAuthController;

import com.example.kingsneaker.entity.ChiTietSanPham;
import com.example.kingsneaker.mapper.ChiTietSanPhamMapper;
import com.example.kingsneaker.request.ChiTietSanPhamRequest;
import com.example.kingsneaker.response.ChiTietSanPhamResponse;
import com.example.kingsneaker.service.ChiTietSanPhamService;
import com.example.kingsneaker.service.HinhAnhService;
import com.example.kingsneaker.service.StorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/chi-tiet-san-pham")
public class ProductController {



        @Autowired
        ChiTietSanPhamService chiTietSanPhamService;
        @Autowired
        ChiTietSanPhamMapper chiTietSanPhamMapper;

        @Autowired
        HinhAnhService hinhAnhService;

        @Autowired
        StorageService storageService;

        @GetMapping("/show-all")
        public ResponseEntity<List<ChiTietSanPhamResponse>> showListCTSP() {
            List<ChiTietSanPham> listCtsp = chiTietSanPhamService.findAll();
            List<ChiTietSanPhamResponse> listCtspDto = new ArrayList<>();
            for (ChiTietSanPham ctsp : listCtsp) {
                listCtspDto.add(chiTietSanPhamMapper.mapToResponse(ctsp));
            }
            return new ResponseEntity<>(listCtspDto, HttpStatus.OK);
        }


    @GetMapping("/detail/{idCtsp}")
    public ResponseEntity<ChiTietSanPhamResponse> showCtsp(@PathVariable("idCtsp") Long idCtsp) {
        ChiTietSanPham ctsp = chiTietSanPhamService.findById(idCtsp);
       ChiTietSanPhamResponse chiTietSanPhamResponse = chiTietSanPhamMapper.mapToResponse(ctsp);
        return new ResponseEntity<>(chiTietSanPhamResponse, HttpStatus.OK);
    }

}
