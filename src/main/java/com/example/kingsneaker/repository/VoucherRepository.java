package com.example.kingsneaker.repository;

import com.example.kingsneaker.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Long> {
    @Modifying
    @Transactional
    @Query(value = "update voucher set so_luong = ?2 where id = ?1",nativeQuery = true)
    void updateVoucherQuantity(Long idVoucher,Integer quantity);

    @Query(value = "SELECT * " +
            "FROM Voucher " +
            "WHERE CAST(GETDATE() AS DATE) BETWEEN CAST(ngay_bat_dau AS DATE) AND CAST(ngay_ket_thuc AS DATE)",
            nativeQuery = true)
    List<Voucher> getListVoucherAvailable();

}
