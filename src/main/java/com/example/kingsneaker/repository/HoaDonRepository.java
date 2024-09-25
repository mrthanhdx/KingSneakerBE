package com.example.kingsneaker.repository;

import com.example.kingsneaker.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface HoaDonRepository extends JpaRepository<HoaDon,Long> {
    @Modifying
    @Transactional
    @Query(value = "update hoa_don set id_khach_hang = ?1 where id = ?2",nativeQuery = true)
    void addIdCustomerToInvoice(Long idCustomer, Long idInvoice);


    @Modifying
    @Transactional
    @Query(value = "update hoa_don set id_voucher = ?1 where id = ?2",nativeQuery = true)
    void addIdVoucherToInvoice(Long idVoucher, Long idInvoice);
}
