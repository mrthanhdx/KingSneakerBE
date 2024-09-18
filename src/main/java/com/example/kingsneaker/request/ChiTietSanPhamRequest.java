package com.example.kingsneaker.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ChiTietSanPhamRequest {

    private Long id;
    @NotNull(message = "giaBan must not be null")
    private Double giaBan;

    @NotNull(message = "soLuong must not be null")
    private Integer soLuong;

    @NotNull(message = "trangThai must not be null")
    @NotBlank
    private String trangThai;


    @NotNull(message = "sanPham must not be null")
    private Long idSanPham;


    @NotNull(message = "mau sac must not be null")
    private Long idMauSac;

    @NotNull(message = "kich co must not be null")
    private Long idKichCo;

    @NotNull(message = "chatLieu must not be null")
    private Long idChatLieu;

    @NotNull(message = "Kieu Dang must not be null")
    private Long idKieuDang;

    private Long idKhuyenMai;
    @NotNull(message = "NSX must not be null")
    private Long idNsx;

    @NotNull(message = "ThuongHieu must not be null")
    private Long idThuongHieu;



}
