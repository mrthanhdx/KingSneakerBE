package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.entity.KichCo;
import com.example.kingsneaker.service.KichCoService;
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
@RequestMapping("/api/v1/kich-co")
public class KichCoController {
    @Autowired
    KichCoService service;

    @GetMapping("/show-all")
    public ResponseEntity<List<KichCo>> showListKichCo(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/new-kich-co")
    public ResponseEntity<KichCo> newKichCo(@RequestBody @Valid KichCo kichCo){
        service.save(kichCo);
        return new ResponseEntity<>(kichCo,HttpStatus.OK);
    }

    @PutMapping("/update-kich-co/{idKichCo}")
    public ResponseEntity<KichCo> updatekichCo(@RequestBody @Valid KichCo kichCo,@PathVariable("idKichCo") Long idKichCo){
        kichCo.setId(idKichCo);
        service.save(kichCo);
        return ResponseEntity.ok(kichCo);
    }

    @DeleteMapping("/delete-kich-co/{idKichCo}")
    public ResponseEntity<KichCo> deleteKichCo(@PathVariable("idKichCo") Long idKichCo) {
        KichCo kichCo =  service.findById(idKichCo);
        service.deleteById(idKichCo);
        return ResponseEntity.ok(kichCo);
    }

}
