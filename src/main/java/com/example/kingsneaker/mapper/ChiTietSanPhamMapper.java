package com.example.kingsneaker.mapper;

import com.example.kingsneaker.dto.ChiTietSanPhamDto;
import com.example.kingsneaker.entity.ChatLieu;
import com.example.kingsneaker.entity.ChiTietSanPham;
import com.example.kingsneaker.entity.KhuyenMai;
import com.example.kingsneaker.entity.KichCo;
import com.example.kingsneaker.entity.KieuDang;
import com.example.kingsneaker.entity.MauSac;
import com.example.kingsneaker.entity.NSX;
import com.example.kingsneaker.entity.SanPham;
import com.example.kingsneaker.entity.ThuongHieu;
import com.example.kingsneaker.service.ChatLieuService;
import com.example.kingsneaker.service.KhuyenMaiService;
import com.example.kingsneaker.service.KichCoService;
import com.example.kingsneaker.service.KieuDangService;
import com.example.kingsneaker.service.MauSacService;
import com.example.kingsneaker.service.NsxService;
import com.example.kingsneaker.service.SanPhamService;
import com.example.kingsneaker.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ChiTietSanPhamMapper {

    @Autowired
    SanPhamService sanPhamService;
    @Autowired
    ChatLieuService chatLieuService;
    @Autowired
    MauSacService mauSacService;
    @Autowired
    NsxService nsxService;
    @Autowired
    KichCoService kichCoService;
    @Autowired
    KieuDangService kieuDangService;
    @Autowired
    KhuyenMaiService khuyenMaiService;

    @Autowired
    ThuongHieuService thuongHieuService;



    public ChiTietSanPhamDto mapToDto(ChiTietSanPham chiTietSanPham) {
        ChiTietSanPhamDto chiTietSanPhamDto = new ChiTietSanPhamDto();
        chiTietSanPhamDto.setId(chiTietSanPham.getId());
        chiTietSanPhamDto.setGiaBan(chiTietSanPham.getGiaBan());
        chiTietSanPhamDto.setSoLuong(chiTietSanPham.getSoLuong());
        chiTietSanPhamDto.setTrangThai(chiTietSanPham.getTrangThai());
        chiTietSanPhamDto.setIdSanPham(chiTietSanPham.getSanPham().getId());
        chiTietSanPhamDto.setIdChatLieu(chiTietSanPham.getChatLieu().getId());
        chiTietSanPhamDto.setIdNsx(chiTietSanPham.getNsx().getId());
        chiTietSanPhamDto.setIdKichCo(chiTietSanPham.getKichCo().getId());
        chiTietSanPhamDto.setIdKieuDang(chiTietSanPham.getKieuDang().getId());
        chiTietSanPhamDto.setIdMauSac(chiTietSanPham.getMauSac().getId());
        chiTietSanPhamDto.setIdKhuyenMai(chiTietSanPham.getKhuyenMai().getId());
        chiTietSanPhamDto.setIdThuongHieu(chiTietSanPham.getThuongHieu().getId());

        return chiTietSanPhamDto;
    }


    public ChiTietSanPham mapToEntity(ChiTietSanPhamDto dto) {
        ChiTietSanPham ctsp = new ChiTietSanPham();
        System.out.println(sanPhamService);
        System.out.println(mauSacService);
        System.out.println(thuongHieuService);
        System.out.println(nsxService);


        ctsp.setId(dto.getId());
        ctsp.setGiaBan(dto.getGiaBan());
        ctsp.setSoLuong(dto.getSoLuong());
        ctsp.setTrangThai(dto.getTrangThai());
        SanPham sanPham = sanPhamService.findById(dto.getIdSanPham());
        ctsp.setSanPham(sanPham);

        ChatLieu chatLieu = chatLieuService.findById(dto.getIdChatLieu());
        ctsp.setChatLieu(chatLieu);

        NSX nsx = nsxService.findById(dto.getIdNsx());
        ctsp.setNsx(nsx);

        KichCo kichCo = kichCoService.findById(dto.getIdKichCo());
        ctsp.setKichCo(kichCo);

        KieuDang kieuDang = kieuDangService.findById(dto.getIdKieuDang());
        ctsp.setKieuDang(kieuDang);

        MauSac mauSac = mauSacService.findById(dto.getIdMauSac());
        ctsp.setMauSac(mauSac);

        KhuyenMai khuyenMai = khuyenMaiService.findById(dto.getIdKhuyenMai());
        ctsp.setKhuyenMai(khuyenMai);

        ThuongHieu thuongHieu = thuongHieuService.findById(dto.getIdThuongHieu());
        ctsp.setThuongHieu(thuongHieu);

        return ctsp;
    }

}
