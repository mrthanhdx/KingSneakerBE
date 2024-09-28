package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.HoaDon;
import com.example.kingsneaker.repository.HoaDonRepository;
import com.example.kingsneaker.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    HoaDonRepository hoaDonRepository;
    @Override
    public void save(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public void deleteById(Long id) {
        hoaDonRepository.deleteById(id);
    }

    @Override
    public HoaDon findById(Long id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public void addIdCustomerToInvoice(Long idCustomer, Long idInvoice) {
        hoaDonRepository.addIdCustomerToInvoice(idCustomer,idInvoice);
    }

    @Override
    public void addIdVoucherToInvoice(Long idVoucher, Long idInvoice) {
        hoaDonRepository.addIdVoucherToInvoice(idVoucher,idInvoice);
    }

    @Override
    public void checkoutHD(Double tienKhachTra, Double tienThua, Double doanhThu, String ghiChu, Long idHD) {
        hoaDonRepository.checkoutHD(tienKhachTra,tienThua,doanhThu,ghiChu,idHD);
    }

    @Override
    public List<HoaDon> getListInvoiceCounter() {
        return hoaDonRepository.getListInvoiceCounter();
    }

    @Override
    public void cancelHoaDon(Long idHoaDon) {
        hoaDonRepository.cancelHoaDon(idHoaDon);
    }
}
