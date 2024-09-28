package com.example.kingsneaker.controller.sellAtCounterController;

import com.example.kingsneaker.entity.ChiTietSanPham;
import com.example.kingsneaker.entity.HoaDon;
import com.example.kingsneaker.entity.HoaDonChiTiet;
import com.example.kingsneaker.entity.User;
import com.example.kingsneaker.service.ChiTietSanPhamService;
import com.example.kingsneaker.service.HoaDonChiTietService;
import com.example.kingsneaker.service.HoaDonService;
import com.example.kingsneaker.service.VoucherService;
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
@RequestMapping("/api/v1/hoa-don")

public class HoaDonController {

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    VoucherService voucherService;

    @GetMapping("/show-all")
    public ResponseEntity<List<HoaDon>> showAllHoaDon() {
        List<HoaDon> listHD = hoaDonService.findAll();
        return new ResponseEntity<>(listHD, HttpStatus.OK);
    }

    @GetMapping("/show-listInvoice-counter")
    public ResponseEntity<List<HoaDon>> showListInvoiceAtCounter() {
        List<HoaDon> listHD = hoaDonService.getListInvoiceCounter();
        return new ResponseEntity<>(listHD, HttpStatus.OK);
    }


    @GetMapping("/detail/{idHD}")
    public ResponseEntity<HoaDon> getDetailHoaDon(@PathVariable("idHD") Long idHD) {
        HoaDon hoaDon = hoaDonService.findById(idHD);
        return new ResponseEntity<>(hoaDon, HttpStatus.OK);
    }

    @PostMapping("/new-hoa-don")
    public ResponseEntity<?> newHoaDon() {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa("HD-" + (int) (Math.random() * 999999));
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

    @PutMapping("/add-customer-to-invoice/{idInvoice}")
    public ResponseEntity<?> addCustomerToInvoice(@PathVariable("idInvoice") Long idInvoice, @RequestParam("idCustomer") Long idCustomer) {
        try {
            hoaDonService.addIdCustomerToInvoice(idCustomer, idInvoice);
            return new ResponseEntity<>("add customer to invoice successfully !", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("add failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/add-voucher-to-invoice/{idInvoice}")
    public ResponseEntity<?> addVoucherToInvoice(@PathVariable("idInvoice") Long idInvoice, @RequestParam("idVoucher") Long idVoucher) {
        try {
            hoaDonService.addIdVoucherToInvoice(idVoucher, idInvoice);
            return new ResponseEntity<>("add voucher to invoice successfully !", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("add failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/checkout")
    public ResponseEntity<?> checkoutInvoice(@RequestParam("tienKhachTra") Double tienKhachTra,
                                             @RequestParam("idHD") Long idHD,
                                             @RequestParam("doanhThu") Double doanhThu,
                                             @RequestParam("ghiChu") String ghiChu,
                                             @RequestParam("tienThua") Double tienThua) {
        try {
            HoaDon hoaDon = hoaDonService.findById(idHD);
            if (hoaDon.getVoucher()!=null) {
                Integer newVoucherQuantity = hoaDon.getVoucher().getSoLuong()-1;
                Long idVoucher = hoaDon.getVoucher().getId();
                voucherService.updateVoucherQuantity(idVoucher,newVoucherQuantity);
            }
            hoaDonService.checkoutHD(tienKhachTra, tienThua, doanhThu, ghiChu, idHD);
            return new ResponseEntity<>("Checkout Thanh Cong", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Check Out That bai", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/cancel-hoa-don/{idHD}")
    public ResponseEntity<?> deleteHoaDon(@PathVariable("idHD") Long idHD) {
        try {
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
            hoaDonService.cancelHoaDon(idHD);
            return new ResponseEntity<>("Huy Thanh Cong", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Huy That Bai", HttpStatus.BAD_REQUEST);
        }

    }
}
