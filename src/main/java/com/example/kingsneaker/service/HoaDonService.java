package com.example.kingsneaker.service;

import com.example.kingsneaker.entity.HoaDon;

import java.util.List;

public interface HoaDonService extends CommonService<HoaDon>{
    void addIdCustomerToInvoice(Long idCustomer,Long idInvoice);


    void addIdVoucherToInvoice(Long idVoucher,Long idInvoice);

    void checkoutHD(Double tienKhachTra,Double tienThua,Double doanhThu,String ghiChu,Long idHD);

    List<HoaDon> getListInvoiceCounter();

    void cancelHoaDon(Long idHoaDon);

}
