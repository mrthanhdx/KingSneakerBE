package com.example.kingsneaker.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "hoa_don")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_khach_hang")
    private User khachHang;

    @ManyToOne
    @JoinColumn(name = "id_nhan_vien")
    private User nhanVien;
    @Column(name = "ma")
    private String ma;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "ngay_thanh_toan")
    private LocalDateTime ngayThanhToan;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "tong_tien")
    private Double tongTien;

    @Column(name = "tien_khach_tra")
    private Double tienKhachTra;

    @Column(name = "tien_thua")
    private Double tienThua;


    @Column(name = "doanh_thu")
    private Double doanhThu;

    @Column(name = "loai_don")
    private Integer loaiDon;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "id_voucher")
    private Voucher voucher;

//    @PreUpdate
//    protected void onUpdate() {
//        ngayThanhToan = LocalDateTime.now();
//    }
    // Getters and setters (omitted for brevity)
}
