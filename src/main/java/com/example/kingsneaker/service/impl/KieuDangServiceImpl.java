package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.KieuDang;
import com.example.kingsneaker.repository.KieuDangRepository;
import com.example.kingsneaker.service.KieuDangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KieuDangServiceImpl implements KieuDangService {

    @Autowired
    KieuDangRepository kieuDangRepository;
    @Override
    public void save(KieuDang kieuDang) {
        kieuDangRepository.save(kieuDang);
    }

    @Override
    public void deleteById(Long id) {
        kieuDangRepository.deleteById(id);
    }

    @Override
    public KieuDang findById(Long id) {
        return kieuDangRepository.findById(id).orElse(null);
    }

    @Override
    public List<KieuDang> findAll() {
        return kieuDangRepository.findAll();
    }
}
