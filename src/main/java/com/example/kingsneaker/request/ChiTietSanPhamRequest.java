package com.example.kingsneaker.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ChiTietSanPhamRequest {

    private Long id;
    @NotNull
    private Double giaBan;
    @NotNull
    private Integer soLuong;
    @NotNull
    @NotBlank
    private String trangThai;

    @NotNull
    private MultipartFile image;
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

    private Long idKhuyenMai;

    @NotNull
    private Long idNsx;

    @NotNull
    private Long idThuongHieu;



}
