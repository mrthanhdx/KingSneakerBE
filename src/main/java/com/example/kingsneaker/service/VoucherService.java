package com.example.kingsneaker.service;

import com.example.kingsneaker.entity.Voucher;

public interface VoucherService extends CommonService<Voucher> {

    void updateVoucherQuantity(Long idVoucher,Integer voucherQuantity);
}
