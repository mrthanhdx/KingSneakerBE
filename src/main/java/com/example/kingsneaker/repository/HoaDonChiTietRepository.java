package com.example.kingsneaker.repository;

import com.example.kingsneaker.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet,Long> {
    @Query(value = "select * from hoa_don_chi_tiet where id_hoa_don = ?1",nativeQuery = true)
    List<HoaDonChiTiet> getHoaDonChiTietsByIdHd(Long idHD);
}
