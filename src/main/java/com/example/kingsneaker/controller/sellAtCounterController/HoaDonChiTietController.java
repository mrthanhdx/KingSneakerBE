package com.example.kingsneaker.controller.sellAtCounterController;

import com.example.kingsneaker.entity.ChiTietSanPham;
import com.example.kingsneaker.entity.HoaDon;
import com.example.kingsneaker.entity.HoaDonChiTiet;
import com.example.kingsneaker.entity.User;
import com.example.kingsneaker.service.ChiTietSanPhamService;
import com.example.kingsneaker.service.HoaDonChiTietService;
import com.example.kingsneaker.service.HoaDonService;
import com.example.kingsneaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    UserService userService;

    @GetMapping("/get-list-hdct/{idHD}")
    public ResponseEntity<?> getListHDCTByIdHD(@PathVariable("idHD") Long idHD) {
        List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getListHDCTById(idHD);
        return new ResponseEntity<>(listHDCT, HttpStatus.OK);
    }

    @GetMapping("/get-all-customer")
    public ResponseEntity<List<User>> getAllCustomer() {
        return new ResponseEntity<>(userService.getAllCustomer(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-hdct")
    public ResponseEntity<?> deleteHdct(@RequestParam("idCtsp") Long idCTSP, @RequestParam("idHd") Long idHD) {
        System.out.println(idCTSP + "---" + idHD);
        try {
            HoaDonChiTiet hdct = hoaDonChiTietService.getHoaDonChiTietsByIdHdAndIdCtsp(idHD, idCTSP);
            Integer soLuongHDCT = hdct.getSoLuong();
            ChiTietSanPham chiTietSanPham = chiTietSanPhamService.findById(idCTSP);
            chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + soLuongHDCT);
            chiTietSanPhamService.save(chiTietSanPham);
            hoaDonChiTietService.deleteHDCTByIdHdAndIdSpct(idHD, idCTSP);
            HoaDon hoaDon = hoaDonService.findById(idHD);
            Double tongTien = Double.valueOf(0);
            List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getListHDCTById(idHD);
            for (HoaDonChiTiet hdct1 : listHDCT) {
                tongTien += hdct1.getDonGia() * hdct1.getSoLuong();
            }
            hoaDon.setTongTien(tongTien);
            hoaDon.setId(idHD);
            if (hoaDon.getVoucher() != null) {
                Double giaTriToiThieu = hoaDon.getVoucher().getGiaTriToiThieu();
                if (hoaDon.getTongTien() < giaTriToiThieu) {
                    hoaDon.setVoucher(null);
                }
            }
            hoaDonService.save(hoaDon);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("loi delete san pham", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("xoa thanh cong", HttpStatus.OK);
    }

    @DeleteMapping("/delete-all-hdct/{idHD}")
    public ResponseEntity<?> deleteAllHdct(@PathVariable("idHD") Long idHD) {
        HoaDon hoaDon = hoaDonService.findById(idHD);
        List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getListHDCTById(idHD);
        List<Long> listIdHDCT = new ArrayList<>();
        for (HoaDonChiTiet hdct : listHDCT) {
            ChiTietSanPham chiTietSanPham = hdct.getChiTietSanPham();
            chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + hdct.getSoLuong());
            chiTietSanPhamService.save(chiTietSanPham);
            listIdHDCT.add(hdct.getId());
        }
        for (Long id : listIdHDCT) {
            hoaDonChiTietService.deleteById(id);
        }

        Double tongTien = Double.valueOf(0);
        List<HoaDonChiTiet> listHDCT1 = hoaDonChiTietService.getListHDCTById(hoaDon.getId());
        for (HoaDonChiTiet hdct1 : listHDCT1
        ) {
            tongTien += hdct1.getDonGia() * hdct1.getSoLuong();
        }
        hoaDon.setTongTien(tongTien);
        hoaDon.setVoucher(null);
        hoaDonService.save(hoaDon);
        return new ResponseEntity<>("xoa thanh cong", HttpStatus.OK);
    }

    @PostMapping("/new-hdct")
    public ResponseEntity<?> newHDCT(@RequestParam("soLuong") Integer soLuong,
                                     @RequestParam("idCTSP") Long idCTSP,
                                     @RequestParam("idHD") Long idHD) {

        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.findById(idCTSP);
        HoaDon hoaDon = hoaDonService.findById(idHD);
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        Integer soLuongTon = chiTietSanPham.getSoLuong();
        if (soLuong > soLuongTon) {
            return new ResponseEntity<>("so Luong mua khong hop le, Chi con " + soLuongTon + " san pham", HttpStatus.BAD_REQUEST);
        } else if (soLuong < 0) {
            return new ResponseEntity<>("so Luong mua khong hop le, so luong phai lon hon 0", HttpStatus.BAD_REQUEST);
        }

        if (hoaDonChiTietService.isHdctExist(idCTSP, idHD)) {
            HoaDonChiTiet hdct = hoaDonChiTietService.getHoaDonChiTietsByIdHdAndIdCtsp(idHD, idCTSP);
            hdct.setSoLuong(hdct.getSoLuong() + soLuong);
            hoaDonChiTietService.save(hdct);
            chiTietSanPhamService.updateSoluongCTSP(idCTSP, soLuongTon - soLuong);
            Double tongTien = Double.valueOf(0);
            List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getListHDCTById(idHD);
            for (HoaDonChiTiet hdct1 : listHDCT
            ) {
                tongTien += hdct1.getDonGia() * hdct1.getSoLuong();
            }
            hoaDon.setTongTien(tongTien);
            hoaDon.setId(idHD);
            hoaDonService.save(hoaDon);
            return new ResponseEntity<>("Them Sp Thanh Cong", HttpStatus.OK);
        }

        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setDonGia(chiTietSanPham.getGiaBan());
        hoaDonChiTiet.setChiTietSanPham(chiTietSanPham);
        hoaDonChiTiet.setSoLuong(soLuong);
        hoaDonChiTiet.setNgayTao(LocalDateTime.now());
        hoaDonChiTietService.save(hoaDonChiTiet);
        chiTietSanPhamService.updateSoluongCTSP(idCTSP, soLuongTon - soLuong);
        Double tongTien = Double.valueOf(0);
        List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getListHDCTById(idHD);
        for (HoaDonChiTiet hdct1 : listHDCT
        ) {
            tongTien += hdct1.getDonGia() * hdct1.getSoLuong();
        }
        hoaDon.setTongTien(tongTien);
        hoaDon.setId(idHD);
        hoaDonService.save(hoaDon);
        return new ResponseEntity<>("Them Sp Thanh Cong", HttpStatus.OK);
    }

    @PutMapping("/update-quantity")
    public ResponseEntity<?> updateProductQuantityInHDCT(@RequestParam("idHDCT") Long idHDCT,
                                                         @RequestParam("soLuongUpdate") Integer soLuongUpdate,
                                                         @RequestParam("idHD") Long idHD) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.findById(idHDCT);
        Integer soLuongHienCo = hoaDonChiTiet.getChiTietSanPham().getSoLuong();
//        HoaDon hoaDon = hoaDonChiTiet.getHoaDon();
        HoaDon hoaDon = hoaDonService.findById(idHD);;

        if (soLuongUpdate > soLuongHienCo + hoaDonChiTiet.getSoLuong()) {
            return new ResponseEntity<>("so Luong update khong hop le, Chi con " + soLuongHienCo + " san pham nay", HttpStatus.BAD_REQUEST);
        } else if (soLuongUpdate < 0) {
            return new ResponseEntity<>("so Luong update khong hop le, so luong phai lon hon 0", HttpStatus.BAD_REQUEST);
        }
        Integer soLuongChechLech = soLuongUpdate - hoaDonChiTiet.getSoLuong();
        hoaDonChiTiet.setSoLuong(soLuongUpdate);
        ChiTietSanPham ctsp = chiTietSanPhamService.findById(hoaDonChiTiet.getChiTietSanPham().getId());
        ctsp.setSoLuong(ctsp.getSoLuong() - soLuongChechLech);
        chiTietSanPhamService.save(ctsp);
        Double tongTien = Double.valueOf(0);
        List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getListHDCTById(hoaDon.getId());
        for (HoaDonChiTiet hdct1 : listHDCT) {
            tongTien += hdct1.getDonGia() * hdct1.getSoLuong();
        }
        hoaDon.setTongTien(tongTien);
        hoaDon.setId(idHD);
        if (hoaDon.getVoucher() != null) {
            Double giaTriToiThieu = hoaDon.getVoucher().getGiaTriToiThieu();
            System.out.println("GTTT: "+giaTriToiThieu);
            System.out.println("Tong tien: "+ hoaDon.getTongTien());
            if (hoaDon.getTongTien() < giaTriToiThieu) {
                hoaDon.setVoucher(null);
            }
        }

        hoaDonService.save(hoaDon);
        return new ResponseEntity<>("Update So Luong sp Thanh Cong", HttpStatus.OK);
    }
}
