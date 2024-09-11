package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.entity.KichCo;
import com.example.kingsneaker.service.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/kich-co")
public class KichCoController {

    @Autowired
    KichCoService kichCoService;

    @GetMapping("/show-all")
    public ResponseEntity<List<KichCo>> showAllKichCo(){
        return new ResponseEntity<>(kichCoService.findAll(), HttpStatus.OK);
    }
}
