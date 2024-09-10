package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.KhuyenMai;
import com.example.kingsneaker.repository.KhuyenMaiRepository;
import com.example.kingsneaker.service.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KhuyenMaiServiceImpl implements KhuyenMaiService {

    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;
    @Override
    public void save(KhuyenMai khuyenMai) {
        khuyenMaiRepository.save(khuyenMai);
    }

    @Override
    public void deleteById(Long id) {
        khuyenMaiRepository.deleteById(id);
    }

    @Override
    public KhuyenMai findById(Long id) {
        return khuyenMaiRepository.findById(id).orElse(null);
    }

    @Override
    public List<KhuyenMai> findAll() {
        return khuyenMaiRepository.findAll();
    }
}
