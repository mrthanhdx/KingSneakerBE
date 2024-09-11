package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.entity.MauSac;
import com.example.kingsneaker.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mau-sac")
public class MauSacController {
    @Autowired
    MauSacService mauSacService;

    @GetMapping("/show-all")
    public ResponseEntity<List<MauSac>> showListMauSac(){
        return new ResponseEntity<>(mauSacService.findAll(), HttpStatus.OK);
    }


}
