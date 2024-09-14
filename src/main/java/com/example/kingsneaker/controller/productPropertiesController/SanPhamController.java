package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.entity.SanPham;
import com.example.kingsneaker.service.SanPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/san-pham")
@CrossOrigin(origins = "*",methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST ,RequestMethod.DELETE})

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
        sanPham.setNgaySua(LocalDateTime.now());
        sanPham.setNgayTao(LocalDateTime.now());
        sanPhamService.save(sanPham);
        return new ResponseEntity<>(sanPham,HttpStatus.OK);
    }

    @PutMapping("/update-san-pham/{idSp}")
    public ResponseEntity<SanPham> updateProduct(@RequestBody @Valid SanPham sanPham,@PathVariable("idSp") Long idSp){
        sanPham.setId(idSp);
        sanPhamService.save(sanPham);
        return new ResponseEntity<>(sanPham,HttpStatus.OK);
    }

//    @DeleteMapping("/delete-san-pham/{idSp}")
//    public ResponseEntity<SanPham> deleteProduct( @PathVariable("idSp") Long idSp){
//        SanPham sanPham = sanPhamService.findById(idSp);
//        sanPhamService.deleteById(idSp);
//        return new ResponseEntity<>(sanPham,HttpStatus.OK);
//    }

    @PutMapping("/update-trang-thai/{idSp}")
    public ResponseEntity<?> updateTrangThai(@PathVariable("idSp") Long idsp, @RequestParam("status") int  status){
        sanPhamService.updateStatusProduct(idsp,status);
        return ResponseEntity.ok("update successfully !");
    }
}
