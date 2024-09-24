package com.example.kingsneaker.controller.sellAtCounterController;

import com.example.kingsneaker.entity.ChiTietSanPham;
import com.example.kingsneaker.entity.HoaDon;
import com.example.kingsneaker.entity.HoaDonChiTiet;
import com.example.kingsneaker.service.ChiTietSanPhamService;
import com.example.kingsneaker.service.HoaDonChiTietService;
import com.example.kingsneaker.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hoa-don-chi-tiet")
public class HoaDonChiTietController {
    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;


    @GetMapping("/get-list-hdct/{idHD}")
    public ResponseEntity<?> getListHDCTByIdHD(@PathVariable("idHD") Long idHD){
       List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getListHDCTById(idHD);
       return new ResponseEntity<>(listHDCT,HttpStatus.OK);
    }

    @PostMapping("/new-hdct")
    public ResponseEntity<?> newHDCT(@RequestParam("soLuong") Integer soLuong,
                                     @RequestParam("idCTSP") Long idCTSP,
                                     @RequestParam("idHD") Long idHD) {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.findById(idCTSP);
        HoaDon hoaDon = hoaDonService.findById(idHD);
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        Integer soLuongTon = chiTietSanPham.getSoLuong();
        if (soLuong>soLuongTon) {
            return new ResponseEntity<>("so Luong mua khong hop le", HttpStatus.BAD_REQUEST);
        }
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setDonGia(chiTietSanPham.getGiaBan());
        hoaDonChiTiet.setChiTietSanPham(chiTietSanPham);
        hoaDonChiTiet.setSoLuong(soLuong);
        hoaDonChiTiet.setNgayTao(LocalDateTime.now());
        hoaDonChiTietService.save(hoaDonChiTiet);
        chiTietSanPhamService.updateSoluongCTSP(idCTSP,soLuongTon-soLuong);
        Double tongTien = soLuong*chiTietSanPham.getGiaBan();
        hoaDon.setTongTien(tongTien);
        hoaDon.setId(idHD);
        hoaDonService.save(hoaDon);
        return new ResponseEntity<>("Them Sp Thanh Cong",HttpStatus.OK);
    }
}
