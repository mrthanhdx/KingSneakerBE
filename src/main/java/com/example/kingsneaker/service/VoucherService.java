package com.example.kingsneaker.service;

import com.example.kingsneaker.entity.Voucher;

import java.util.List;

public interface VoucherService extends CommonService<Voucher> {

    void updateVoucherQuantity(Long idVoucher,Integer voucherQuantity);
    List<Voucher> getListVoucherAvaliable();
}
