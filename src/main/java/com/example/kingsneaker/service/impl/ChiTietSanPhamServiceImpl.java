package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.ChiTietSanPham;
import com.example.kingsneaker.repository.ChiTietSanPhamRepository;
import com.example.kingsneaker.request.ChiTietSanPhamRequest;
import com.example.kingsneaker.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {
    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Override
    public void save(ChiTietSanPham chiTietSanPham) {
        chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public void deleteById(Long id) {
    chiTietSanPhamRepository.deleteById(id);
    }

    @Override
    public ChiTietSanPham findById(Long id) {
        return chiTietSanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public List<ChiTietSanPham> findAll() {
        return chiTietSanPhamRepository.findAll();
    }

    @Override
    public void validateProductDetails(ChiTietSanPhamRequest ctspRequest) {
        if (ctspRequest.getGiaBan() == null || ctspRequest.getGiaBan() <= 0) {
            throw new IllegalArgumentException("GiaBan must be greater than 0.");
        }
        if (ctspRequest.getSoLuong() == null || ctspRequest.getSoLuong() <= 0) {
            throw new IllegalArgumentException("SoLuong must be greater than 0.");
        }
        if (ctspRequest.getIdSanPham() == null) {
            throw new IllegalArgumentException("IdSanPham is required.");
        }
    }

    @Override
    public void updateSoluongCTSP(Long idCTSP, Integer soLuong) {
        chiTietSanPhamRepository.UpdateSoLuongSpctById(idCTSP,soLuong);
    }
}
