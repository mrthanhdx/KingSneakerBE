package com.example.kingsneaker.service;

import com.example.kingsneaker.entity.SanPham;

public interface SanPhamService extends CommonService<SanPham>{
    public void updateStatusProduct(Long idSp,int status);
}
