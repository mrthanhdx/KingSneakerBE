package com.example.kingsneaker.dto;

import com.example.kingsneaker.entity.ChatLieu;
import com.example.kingsneaker.entity.HinhAnh;
import com.example.kingsneaker.entity.KhuyenMai;
import com.example.kingsneaker.entity.KichCo;
import com.example.kingsneaker.entity.KieuDang;
import com.example.kingsneaker.entity.MauSac;
import com.example.kingsneaker.entity.NSX;
import com.example.kingsneaker.entity.SanPham;
import com.example.kingsneaker.entity.ThuongHieu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class ChiTietSanPhamDto {
    private Long id;
    @NotNull
    private Double giaBan;
    @NotNull
    private Integer soLuong;
    @NotNull
    @NotBlank
    private String trangThai;

    @NotNull
    private Long idSanPham;

    @NotNull
    private Long idMauSac;

    @NotNull
    private Long idKichCo;

    @NotNull
    private Long idChatLieu;

    @NotNull
    private Long idKieuDang;

    @NotNull
    private Long idKhuyenMai;

    @NotNull
    private Long idNsx;

    @NotNull
    private Long idThuongHieu;

//    private Long idHinhAnh;
}
