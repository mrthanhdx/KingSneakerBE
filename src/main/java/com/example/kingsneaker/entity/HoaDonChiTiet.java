package com.example.kingsneaker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "hoa_don_chi_tiet")
public class HoaDonChiTiet extends CreatedUpdatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_chi_tiet_sp")
    private ChiTietSanPham chiTietSanPham;

    @ManyToOne
    @JoinColumn(name = "id_hoa_don")
    private HoaDon hoaDon;

    @Column(name = "don_gia")
    private Float donGia;

    @Column(name = "so_luong")
    private Integer soLuong;

//    @Column(name = "ngay_tao")
//    private Date ngayTao;
//
//    @Column(name = "ngay_sua")
//    private Date ngaySua;

    // Getters and setters (omitted for brevity)
}
