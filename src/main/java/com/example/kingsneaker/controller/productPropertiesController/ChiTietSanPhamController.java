package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.entity.HinhAnh;
import com.example.kingsneaker.request.ChiTietSanPhamRequest;
import com.example.kingsneaker.response.ChiTietSanPhamResponse;
import com.example.kingsneaker.entity.ChiTietSanPham;
import com.example.kingsneaker.mapper.ChiTietSanPhamMapper;
import com.example.kingsneaker.service.ChiTietSanPhamService;
import com.example.kingsneaker.service.HinhAnhService;
import com.example.kingsneaker.service.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    HinhAnhService hinhAnhService;

    @Autowired
    StorageService storageService;

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
    public ResponseEntity<?> newCTSP(
            @RequestPart("image") MultipartFile image,
            @RequestPart("details") String details) {  // Change to String to manually parse the JSON
        try {
            // Convert JSON string (details) to the ChiTietSanPhamRequest object
            ObjectMapper objectMapper = new ObjectMapper();
            ChiTietSanPhamRequest ctspRequest = objectMapper.readValue(details, ChiTietSanPhamRequest.class);

            System.out.println(details);
            System.out.println(image);
            System.out.println(ctspRequest);
            //validate
            if (ctspRequest.getGiaBan() == null || ctspRequest.getGiaBan() <= 0) {
                return new ResponseEntity<>("GiaBan must be greater than 0",HttpStatus.NOT_FOUND);
            }
            if (ctspRequest.getSoLuong() == null || ctspRequest.getSoLuong() <= 0) {
                System.out.println(ctspRequest.getSoLuong());
                return new ResponseEntity<>("soLuong must be greater than 0",HttpStatus.NOT_FOUND);
            }

            // Map the product details to entity
            ChiTietSanPham ctsp = chiTietSanPhamMapper.mapToEntity(ctspRequest);

            // Handle image upload
            HinhAnh hinhAnh = new HinhAnh();
            String fileName = storageService.store(image);  // Store the image
            hinhAnh.setPath(fileName);
            hinhAnhService.save(hinhAnh);  // Save image entity

            ctsp.setHinhAnh(hinhAnh);  // Set the image entity to the product

            // Save the product entity
            service.save(ctsp);

            // Map to response DTO
            ChiTietSanPhamResponse response = chiTietSanPhamMapper.mapToResponse(ctsp);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Add Product Detail failed !");
        }
    }


    @PutMapping(path = "/update-chi-tiet-san-pham/{idCTSP}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateCTSP(
            @RequestPart("image") MultipartFile image,
            @RequestPart("details") String details,
            @PathVariable("idCTSP") Long idCTSP) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ChiTietSanPhamRequest ctspRequest = objectMapper.readValue(details, ChiTietSanPhamRequest.class);
            System.out.println(idCTSP);
            System.out.println(details);
            System.out.println(image);
            System.out.println(ctspRequest);
            //validate
            if (ctspRequest.getGiaBan() == null || ctspRequest.getGiaBan() <= 0) {
                return new ResponseEntity<>("GiaBan must be greater than 0",HttpStatus.NOT_FOUND);
            }
            if (ctspRequest.getSoLuong() == null || ctspRequest.getSoLuong() <= 0) {
                System.out.println(ctspRequest.getSoLuong());
                return new ResponseEntity<>("soLuong must be greater than 0",HttpStatus.NOT_FOUND);
            }
            ChiTietSanPham ctsp = chiTietSanPhamMapper.mapToEntity(ctspRequest);

            ctsp.setId(idCTSP);
            service.save(ctsp);


            ChiTietSanPhamResponse response = chiTietSanPhamMapper.mapToResponse(ctsp);
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
