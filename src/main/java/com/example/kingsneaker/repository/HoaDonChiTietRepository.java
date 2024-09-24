package com.example.kingsneaker.repository;

import com.example.kingsneaker.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet,Long> {
    @Query(value = "select * from hoa_don_chi_tiet where id_hoa_don = ?1",nativeQuery = true)
    List<HoaDonChiTiet> getHoaDonChiTietsByIdHd(Long idHD);

    @Query(value = "select * from hoa_don_chi_tiet where id_hoa_don = ?1 and id_chi_tiet_sp = ?2",nativeQuery = true)
    HoaDonChiTiet getHoaDonChiTietsByIdHdAndIdCtsp(Long idHD,Long idCTSP);

    @Modifying
    @Transactional
    @Query(value = "delete from hoa_don_chi_tiet where id_hoa_don= ?1 and id_chi_tiet_sp = ?2",nativeQuery = true)
    void deleteHDCTByIdHdAndIdSpct(Long idHoaDon,Long idSPCT);


    @Query(value = "update hoa_don_chi_tiet set so_luong = ?1 where id_chi_tiet_sp = ?2 and id_hoa_don = ?3",nativeQuery = true)
    void updateSoLuongByIdCtspAndidHd(Integer soLuong,Long idCTSP,Long idHD);


}
