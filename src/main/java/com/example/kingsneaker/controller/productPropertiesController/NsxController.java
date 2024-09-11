package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.entity.NSX;
import com.example.kingsneaker.service.KhuyenMaiService;
import com.example.kingsneaker.service.NsxService;
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
@RequestMapping("/api/v1/NSX")
public class NsxController {

    @Autowired
    NsxService service;

    @GetMapping("/show-all")
    public ResponseEntity<List<NSX>> showListNSX(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/new-nsx")
    public ResponseEntity<NSX> newNSX(@RequestBody @Valid NSX nsx){
        service.save(nsx);
        return new ResponseEntity<>(nsx,HttpStatus.OK);
    }

    @PutMapping("/update-nsx/{idNSX}")
    public ResponseEntity<NSX> updateNSX(@RequestBody @Valid NSX nsx,@PathVariable("idNSX") Long idNSX){
        nsx.setId(idNSX);
        service.save(nsx);
        return ResponseEntity.ok(nsx);
    }

    @DeleteMapping("/delete-nsx/{idNSX}")
    public ResponseEntity<NSX> deleteNSX(@PathVariable("idNSX") Long idNSX) {
        NSX nsx =  service.findById(idNSX);
        service.deleteById(idNSX);
        return ResponseEntity.ok(nsx);
    }
}
