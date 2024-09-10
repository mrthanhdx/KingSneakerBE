package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.entity.SanPham;
import com.example.kingsneaker.service.SanPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/san-pham")
public class SanPhamController {
    @Autowired
    SanPhamService sanPhamService;

    @GetMapping("/show-all")
    public ResponseEntity<List<SanPham>> getAllSanPham(){
       List<SanPham> listsp =  sanPhamService.findAll();
       return new ResponseEntity<>(listsp, HttpStatus.OK);
    }

    @PostMapping("/them-san-pham")
    public ResponseEntity<SanPham> createNewProduct(@RequestBody @Valid SanPham sanPham){
        sanPhamService.save(sanPham);
        return new ResponseEntity<>(sanPham,HttpStatus.OK);
    }

    @PutMapping("/update-san-pham")
    public ResponseEntity<SanPham> updateProduct(@RequestBody @Valid SanPham sanPham,Long idSp){
        sanPham.setId(idSp);
        sanPhamService.save(sanPham);
        return new ResponseEntity<>(sanPham,HttpStatus.OK);
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<SanPham> deleteProduct(Long idSp){
        SanPham sanPham = sanPhamService.findById(idSp);
        sanPhamService.deleteById(idSp);
        return new ResponseEntity<>(sanPham,HttpStatus.OK);
    }
}
