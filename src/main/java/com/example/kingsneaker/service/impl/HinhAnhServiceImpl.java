package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.HinhAnh;
import com.example.kingsneaker.repository.HinhAnhRepository;
import com.example.kingsneaker.service.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HinhAnhServiceImpl implements HinhAnhService {
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @Override
    public void save(HinhAnh hinhAnh) {
        hinhAnhRepository.save(hinhAnh);
    }

    @Override
    public void deleteById(Long id) {
        hinhAnhRepository.deleteById(id);
    }

    @Override
    public HinhAnh findById(Long id) {
        return findById(id);
    }

    @Override
    public List<HinhAnh> findAll() {
        return hinhAnhRepository.findAll();
    }
}
