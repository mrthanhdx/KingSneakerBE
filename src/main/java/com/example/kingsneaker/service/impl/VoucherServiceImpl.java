package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.Voucher;
import com.example.kingsneaker.repository.VoucherRepository;
import com.example.kingsneaker.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    VoucherRepository voucherRepository;

    @Override
    public void save(Voucher voucher) {
        voucherRepository.save(voucher);
    }

    @Override
    public void deleteById(Long id) {
    voucherRepository.deleteById(id);
    }

    @Override
    public Voucher findById(Long id) {
        return voucherRepository.findById(id).orElse(null);
    }

    @Override
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }


    @Override
    public void updateVoucherQuantity(Long idVoucher, Integer voucherQuantity) {
        voucherRepository.updateVoucherQuantity(idVoucher,voucherQuantity);
    }
}
