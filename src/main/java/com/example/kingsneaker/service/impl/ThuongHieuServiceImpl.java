package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.ThuongHieu;
import com.example.kingsneaker.repository.ThuongHieuRepository;
import com.example.kingsneaker.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {

    @Autowired
    ThuongHieuRepository thuongHieuRepository;
    @Override
    public void save(ThuongHieu thuongHieu) {
        thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public void deleteById(Long id) {
        thuongHieuRepository.deleteById(id);
    }

    @Override
    public ThuongHieu findById(Long id) {
        return thuongHieuRepository.findById(id).orElse(null);
    }

    @Override
    public List<ThuongHieu> findAll() {
        return thuongHieuRepository.findAll();
    }
}
