package com.example.kingsneaker.response;

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


public class ChiTietSanPhamResponse {
    private Long id;
    @NotNull
    private Double giaBan;
    @NotNull
    private Integer soLuong;
    @NotNull
    @NotBlank
    private String trangThai;

    private Long idSanPham;

    private String tenSanPham;

    @NotNull
    private Long idMauSac;

    @NotNull
    private Long idKichCo;

    @NotNull
    private Long idChatLieu;

    @NotNull
    private Long idKieuDang;

    private Long idKhuyenMai;

    @NotNull
    private Long idNsx;

    @NotNull
    private Long idThuongHieu;

    private String pathHinhAnh;

    private String mauSac;
    private String nsx;

    private String kieuDang;

    private String kichCo;

    private String chatLieu;

    private String thuongHieu;
}
