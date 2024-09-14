package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.entity.MauSac;
import com.example.kingsneaker.service.MauSacService;
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
@RequestMapping("/api/v1/mau-sac")
@CrossOrigin(origins = "*",methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST ,RequestMethod.DELETE})
public class MauSacController {


    @Autowired
    MauSacService mauSacService;

    @GetMapping("/show-all")
    public ResponseEntity<List<MauSac>> showListMauSac(){
        return new ResponseEntity<>(mauSacService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/new-mau-sac")
    public ResponseEntity<MauSac> newMauSac(@RequestBody @Valid MauSac mauSac){
        mauSacService.save(mauSac);
        return new ResponseEntity<>(mauSac,HttpStatus.OK);
    }

    @PutMapping("/update-mau-sac/{idMauSac}")
    public ResponseEntity<MauSac> updateMauSac(@RequestBody @Valid MauSac mauSac,@PathVariable("idMauSac") Long idMauSac){
        mauSac.setId(idMauSac);
        mauSacService.save(mauSac);
        return ResponseEntity.ok(mauSac);
    }

    @DeleteMapping("/delete-mau-sac/{idMauSac}")
    public ResponseEntity<MauSac> deleteMauSac(@PathVariable("idMauSac") Long idMauSac) {
      MauSac mauSac =  mauSacService.findById(idMauSac);
        mauSacService.deleteById(idMauSac);
        return ResponseEntity.ok(mauSac);
    }

}
