package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.entity.ThuongHieu;
import com.example.kingsneaker.service.ThuongHieuService;
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

import java.util.List;

@RestController
@RequestMapping("/admin/api/v1/thuong-hieu")
@CrossOrigin(origins = "*",methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST ,RequestMethod.DELETE})

public class ThuongHieuController {

    @Autowired
    ThuongHieuService service;

    @GetMapping("/show-all")
    public ResponseEntity<List<ThuongHieu>> showListThuongHieu(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/new-thuong-hieu")
    public ResponseEntity<ThuongHieu> newThuongHieu(@RequestBody @Valid ThuongHieu th){
        service.save(th);
        return new ResponseEntity<>(th,HttpStatus.OK);
    }

    @PutMapping("/update-thuong-hieu/{idThuongHieu}")
    public ResponseEntity<ThuongHieu> updateThuongHieu(@RequestBody @Valid ThuongHieu th,@PathVariable("idThuongHieu") Long idThuongHieu){
        th.setId(idThuongHieu);
        service.save(th);
        return ResponseEntity.ok(th);
    }

    @DeleteMapping("/delete-thuong-hieu/{idThuongHieu}")
    public ResponseEntity<ThuongHieu> deleteThuongHieu(@PathVariable("idThuongHieu") Long idThuongHieu) {
        ThuongHieu th =  service.findById(idThuongHieu);
        service.deleteById(idThuongHieu);
        return ResponseEntity.ok(th);
    }

}
