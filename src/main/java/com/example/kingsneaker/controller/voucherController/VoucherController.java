package com.example.kingsneaker.controller.voucherController;

import com.example.kingsneaker.entity.Voucher;
import com.example.kingsneaker.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/voucher")
public class VoucherController {

    @Autowired
    VoucherService voucherService;

    @GetMapping("/get-voucher-valid/{tongTienHD}")
    public ResponseEntity<List<Voucher>> getListVoucherValid(@PathVariable("tongTienHD") String tongTienHD1) {
//        System.out.println(tongTienHD1);
        Double tongTienHD = Double.parseDouble(tongTienHD1);
        List<Voucher> listAllVoucher = voucherService.findAll();
        List<Voucher> listVoucherValid = new ArrayList<>();
        for (Voucher voucher : listAllVoucher) {
            if (voucher.getGiaTriToiThieu() < tongTienHD
                    && voucher.isVoucherStarted()
                    && !voucher.isVoucherExpired()
                    && voucher.getTrangThai().equalsIgnoreCase("active")
                    && voucher.getSoLuong() > 0) {
                listVoucherValid.add(voucher);
            }
        }
        return new ResponseEntity<>(listVoucherValid, HttpStatus.OK);
    }
}
