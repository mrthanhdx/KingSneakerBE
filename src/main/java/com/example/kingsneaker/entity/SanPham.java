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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "san_pham")
public class SanPham extends CreatedUpdatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma")
    private String ma;

    @NotNull
    @NotBlank
    @Column(name = "ten")
    private String ten;


    @Column(name = "mo_ta")
    private String moTa;


    @Column(name = "trang_thai")
    private String trangThai;

//
//    @Column(name = "ngay_tao")
//    private Date ngayTao;
//
//
//    @Column(name = "ngay_sua")
//    private Date ngaySua;

}
