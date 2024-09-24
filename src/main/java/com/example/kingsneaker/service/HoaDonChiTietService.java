package com.example.kingsneaker.service;

import com.example.kingsneaker.entity.HoaDonChiTiet;

import java.util.List;

public interface HoaDonChiTietService extends CommonService<HoaDonChiTiet>{
    public List<HoaDonChiTiet> getListHDCTById(Long idHD);
}
