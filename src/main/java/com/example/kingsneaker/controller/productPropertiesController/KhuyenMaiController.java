package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.entity.KhuyenMai;
import com.example.kingsneaker.service.ChatLieuService;
import com.example.kingsneaker.service.KhuyenMaiService;
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
@RequestMapping("/api/v1/khuyen-mai")
public class KhuyenMaiController {

    @Autowired
    KhuyenMaiService service;

    @GetMapping("/show-all")
    public ResponseEntity<List<KhuyenMai>> showListKhuyenMai(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/new-khuyen-mai")
    public ResponseEntity<KhuyenMai> newChatLieu(@RequestBody @Valid KhuyenMai km){
        service.save(km);
        return new ResponseEntity<>(km,HttpStatus.OK);
    }

    @PutMapping("/update-khuyen-mai/{idKhuyenMai}")
    public ResponseEntity<KhuyenMai> updateChatLieu(@RequestBody @Valid KhuyenMai km,@PathVariable("idKhuyenMai") Long idKhuyenMai){
        km.setId(idKhuyenMai);
        service.save(km);
        return ResponseEntity.ok(km);
    }

    @DeleteMapping("/delete-khuyen-mai/{idKhuyenMai}")
    public ResponseEntity<KhuyenMai> deleteChatLieu(@PathVariable("idKhuyenMai") Long idKhuyenMai) {
        KhuyenMai km =  service.findById(idKhuyenMai);
        service.deleteById(idKhuyenMai);
        return ResponseEntity.ok(km);
    }
}
