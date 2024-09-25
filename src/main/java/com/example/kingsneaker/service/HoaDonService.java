package com.example.kingsneaker.service;

import com.example.kingsneaker.entity.HoaDon;

public interface HoaDonService extends CommonService<HoaDon>{
    void addIdCustomerToInvoice(Long idCustomer,Long idInvoice);


    void addIdVoucherToInvoice(Long idVoucher,Long idInvoice);

}
