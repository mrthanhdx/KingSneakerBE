package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.HoaDonChiTiet;
import com.example.kingsneaker.repository.HoaDonChiTietRepository;
import com.example.kingsneaker.repository.HoaDonRepository;
import com.example.kingsneaker.service.HoaDonChiTietService;
import com.example.kingsneaker.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
 public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {
    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;
    @Override
    public void save(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public void deleteById(Long id) {
            hoaDonChiTietRepository.deleteById(id);
    }

    @Override
    public HoaDonChiTiet findById(Long id) {
        return hoaDonChiTietRepository.findById(id).orElse(null);
    }

    @Override
    public List<HoaDonChiTiet> findAll() {
        return hoaDonChiTietRepository.findAll();
    }


    @Override
    public List<HoaDonChiTiet> getListHDCTById(Long idHD) {
        return hoaDonChiTietRepository.getHoaDonChiTietsByIdHd(idHD);
    }

    @Override
    public void deleteHDCTByIdHdAndIdSpct(Long idHD, Long idCTSP) {
        hoaDonChiTietRepository.deleteHDCTByIdHdAndIdSpct(idHD,idCTSP);
    }

    @Override
    public Boolean isHdctExist(Long idCTSP, Long idHD) {
        List<HoaDonChiTiet> listAllHDCT = hoaDonChiTietRepository.findAll();
        for (HoaDonChiTiet hdct: listAllHDCT
             ) {
            if (hdct.getChiTietSanPham().getId()==idCTSP&&hdct.getHoaDon().getId()==idHD){
                return true ;
            }
        }
        return false;
    }

    @Override
    public HoaDonChiTiet getHoaDonChiTietsByIdHdAndIdCtsp(Long idHD, Long idCTSP) {
        return hoaDonChiTietRepository.getHoaDonChiTietsByIdHdAndIdCtsp(idHD,idCTSP);
    }
}
