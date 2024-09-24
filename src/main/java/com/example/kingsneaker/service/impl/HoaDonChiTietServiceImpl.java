package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.HoaDonChiTiet;
import com.example.kingsneaker.repository.HoaDonChiTietRepository;
import com.example.kingsneaker.repository.HoaDonRepository;
import com.example.kingsneaker.service.HoaDonChiTietService;
import com.example.kingsneaker.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {
    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;
    @Override
    public void save(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public void deleteById(Long id) {
            hoaDonChiTietRepository.deleteById(id);
    }

    @Override
    public HoaDonChiTiet findById(Long id) {
        return hoaDonChiTietRepository.findById(id).orElse(null);
    }

    @Override
    public List<HoaDonChiTiet> findAll() {
        return hoaDonChiTietRepository.findAll();
    }


    @Override
    public List<HoaDonChiTiet> getListHDCTById(Long idHD) {
        return hoaDonChiTietRepository.getHoaDonChiTietsByIdHd(idHD);
    }
}
