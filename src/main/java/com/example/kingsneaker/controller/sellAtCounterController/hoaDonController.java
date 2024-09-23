package com.example.kingsneaker.controller.sellAtCounterController;

import com.example.kingsneaker.entity.CreatedUpdatedAt;
import com.example.kingsneaker.entity.HoaDon;
import com.example.kingsneaker.entity.User;
import com.example.kingsneaker.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hoa-don")

public class hoaDonController {

    @Autowired
    HoaDonService hoaDonService;

    @GetMapping("/show-all")
    public ResponseEntity<List<HoaDon>> showAllHoaDon(){
        List<HoaDon> listHD = hoaDonService.findAll();
        return new ResponseEntity<>(listHD, HttpStatus.OK);
    }


    @GetMapping("/detail/{idHD}")
    public ResponseEntity<HoaDon> getDetailHoaDon( @PathVariable("idHD") Long idHD) {
        HoaDon hoaDon = hoaDonService.findById(idHD);
        return new ResponseEntity<>(hoaDon,HttpStatus.OK);
    }
    @PostMapping("/new-hoa-don")
    public ResponseEntity<?> newHoaDon(){
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa("HD-"+(int)(Math.random()*999999));
        hoaDon.setNgayTao(LocalDateTime.now());
        hoaDon.setLoaiDon(1);
        hoaDon.setTrangThai(0);
        hoaDon.setTongTien(Double.valueOf(0));
        User user = new User();
        user.setId(Long.valueOf(2));
        hoaDon.setNhanVien(user);
        hoaDonService.save(hoaDon);
        return ResponseEntity.ok("Create invoice success !");
    }
    @DeleteMapping("/delele-hoa-don/{idHD}")
    public ResponseEntity<?> deleteHoaDon(@PathVariable("idHD") Long idHD){
        try {
            hoaDonService.deleteById(idHD);
            return new ResponseEntity<>("Delete Thanh Cong",HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Delete That Bai",HttpStatus.BAD_REQUEST);
        }

    }
}
