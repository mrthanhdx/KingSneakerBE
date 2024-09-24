package com.example.kingsneaker.service;

import com.example.kingsneaker.entity.ChiTietSanPham;
import com.example.kingsneaker.request.ChiTietSanPhamRequest;

public interface ChiTietSanPhamService extends CommonService<ChiTietSanPham>{

    public void validateProductDetails(ChiTietSanPhamRequest chiTietSanPhamRequest);
    public void updateSoluongCTSP(Long idCTSP,Integer soLuong);
}
