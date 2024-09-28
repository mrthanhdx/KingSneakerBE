package com.example.kingsneaker.repository;

import com.example.kingsneaker.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HoaDonRepository extends JpaRepository<HoaDon,Long> {
    @Modifying
    @Transactional
    @Query(value = "update hoa_don set id_khach_hang = ?1 where id = ?2",nativeQuery = true)
    void addIdCustomerToInvoice(Long idCustomer, Long idInvoice);

    @Query(value = "select * from hoa_don where trang_thai = 0 and loai_don = 1 ",nativeQuery = true)
    List<HoaDon> getListInvoiceCounter();


    @Modifying
    @Transactional
    @Query(value = "update hoa_don set id_voucher = ?1 where id = ?2",nativeQuery = true)
    void addIdVoucherToInvoice(Long idVoucher, Long idInvoice);

    @Modifying
    @Transactional
    @Query(value = "update  hoa_don set trang_thai = 1 , tien_khach_tra = ?1 , ngay_thanh_toan = GETDATE(), tien_thua=?2 , doanh_thu=?3 ,  ghi_chu=?4 where id = ?5",nativeQuery = true)
    void checkoutHD(Double tienKhachTra,Double tienThua,Double doanhThu,String ghiChu,Long idHD);

    @Modifying
    @Transactional
    @Query(value = "update hoa_don set trang_thai = 2 where id = ?1",nativeQuery = true)
    void cancelHoaDon(Long idInvoice);
}
