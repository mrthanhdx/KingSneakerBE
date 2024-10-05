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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/v1/chi-tiet-san-pham")
@CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class ChiTietSanPhamController {

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

    @PostMapping("/new-chi-tiet-san-pham")
    public ResponseEntity<?> newCTSP(@ModelAttribute @Valid ChiTietSanPhamRequest chiTietSanPhamRequest) throws IOException {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamMapper.mapToEntity(chiTietSanPhamRequest);
        chiTietSanPhamService.save(chiTietSanPham);
        ChiTietSanPhamResponse chiTietSanPhamResponse = chiTietSanPhamMapper.mapToResponse(chiTietSanPham);
        return new ResponseEntity<>(chiTietSanPhamResponse,HttpStatus.OK);

    }


    @PutMapping( "/update-chi-tiet-san-pham/{idCTSP}")
    public ResponseEntity<?> updateCTSP(
            @ModelAttribute @Valid ChiTietSanPhamRequest chiTietSanPhamRequest,
            @PathVariable("idCTSP") Long idCTSP) throws IOException {
        System.out.println(chiTietSanPhamRequest.getGiaBan());
        ChiTietSanPham chiTietSanPham = chiTietSanPhamMapper.mapToEntity(chiTietSanPhamRequest);
        chiTietSanPham.setId(idCTSP);
        chiTietSanPhamService.save(chiTietSanPham);
        ChiTietSanPhamResponse chiTietSanPhamResponse = chiTietSanPhamMapper.mapToResponse(chiTietSanPham);
        return new ResponseEntity<>(chiTietSanPhamResponse,HttpStatus.OK);


    }

//    @DeleteMapping("/delete-chi-tiet-san-pham/{idCTSP}")
//    public ResponseEntity<ChiTietSanPham> deleteCTSP(@PathVariable("idCTSP") Long idCTSP) {
//        ChiTietSanPham ctsp = service.findById(idCTSP);
//        service.deleteById(idCTSP);
//        return ResponseEntity.ok(ctsp);
//    }

}
