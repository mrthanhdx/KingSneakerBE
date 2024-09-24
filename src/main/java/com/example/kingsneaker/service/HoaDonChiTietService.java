package com.example.kingsneaker.service;

import com.example.kingsneaker.entity.HoaDonChiTiet;

import java.util.List;

public interface HoaDonChiTietService extends CommonService<HoaDonChiTiet>{
    public List<HoaDonChiTiet> getListHDCTById(Long idHD);

    public void deleteHDCTByIdHdAndIdSpct(Long idHD,Long idCTSP);

    public Boolean isHdctExist(Long idCTSP,Long idHD);

    public HoaDonChiTiet getHoaDonChiTietsByIdHdAndIdCtsp(Long idHD, Long idCTSP);
}
