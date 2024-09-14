package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.SanPham;
import com.example.kingsneaker.repository.SanPhamRepository;
import com.example.kingsneaker.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository sanPhamRepository;
    @Override
    public void save(SanPham sanPham) {
        sanPhamRepository.save(sanPham);
    }

    @Override
    public void deleteById(Long id) {
        sanPhamRepository.deleteById(id);
    }

    @Override
    public SanPham findById(Long id) {
        return sanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public List<SanPham> findAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public void updateStatusProduct(Long idSp, int status) {
        sanPhamRepository.updateStatusProduct(idSp,status);
    }
}
