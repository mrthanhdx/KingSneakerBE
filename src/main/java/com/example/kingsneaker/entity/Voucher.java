package com.example.kingsneaker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotBlank
    @Column(name = "ten")
    private String ten;
    @NotNull
    @Column(name = "ngay_bat_dau")
    private Date ngayBatDau;
    @NotNull
    @Column(name = "ngay_ket_thuc")
    private Date ngayKetThuc;
    @NotBlank
    @Column(name = "hinh_thuc")
    private Integer hinhThuc;
    @NotNull
    @Column(name = "gia_tri_giam")
    private Double giaTriGiam;

    private Double giaTriToiThieu;

    private Double giaTriGiamToiDa;
    @NotBlank
    @Column(name = "trang_thai")
    private String trangThai;
    @NotBlank
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @NotBlank
    @Column(name = "ngay_sua")
    private Date ngaySua;
    @NotBlank
    @Column(name = "ma")
    private String ma;
    @NotNull
    @Column(name = "so_luong")
    private Integer soLuong;

    public boolean isVoucherStarted() {
        Date currentDate = new Date();
        return currentDate.after(ngayBatDau) || currentDate.equals(ngayBatDau);
    }

    public boolean isVoucherExpired() {
        Date currentDate = new Date();
        return currentDate.after(ngayKetThuc);
    }
}
