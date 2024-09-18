package com.example.kingsneaker.mapper;

import com.example.kingsneaker.entity.HinhAnh;
import com.example.kingsneaker.request.ChiTietSanPhamRequest;
import com.example.kingsneaker.response.ChiTietSanPhamResponse;
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
import com.example.kingsneaker.service.HinhAnhService;
import com.example.kingsneaker.service.KhuyenMaiService;
import com.example.kingsneaker.service.KichCoService;
import com.example.kingsneaker.service.KieuDangService;
import com.example.kingsneaker.service.MauSacService;
import com.example.kingsneaker.service.NsxService;
import com.example.kingsneaker.service.SanPhamService;
import com.example.kingsneaker.service.StorageService;
import com.example.kingsneaker.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    HinhAnhService hinhAnhService;

    @Autowired
    StorageService storageService;


    public ChiTietSanPhamResponse mapToResponse(ChiTietSanPham chiTietSanPham) {
        ChiTietSanPhamResponse chiTietSanPhamDto = new ChiTietSanPhamResponse();
        chiTietSanPhamDto.setId(chiTietSanPham.getId());
        chiTietSanPhamDto.setGiaBan(chiTietSanPham.getGiaBan());
        chiTietSanPhamDto.setSoLuong(chiTietSanPham.getSoLuong());
        chiTietSanPhamDto.setTrangThai(chiTietSanPham.getTrangThai());
        chiTietSanPhamDto.setPathHinhAnh(chiTietSanPham.getHinhAnh() != null ? chiTietSanPham.getHinhAnh().getphotoPath() : null);
        chiTietSanPhamDto.setTenSanPham(chiTietSanPham.getSanPham() != null ? chiTietSanPham.getSanPham().getTen() : null);
        chiTietSanPhamDto.setIdSanPham(chiTietSanPham.getSanPham() != null ? chiTietSanPham.getSanPham().getId() : null);
        chiTietSanPhamDto.setChatLieu(chiTietSanPham.getChatLieu() != null ? chiTietSanPham.getChatLieu().getTen() : null);
        chiTietSanPhamDto.setIdChatLieu(chiTietSanPham.getChatLieu() != null ? chiTietSanPham.getChatLieu().getId() : null);
        chiTietSanPhamDto.setNsx(chiTietSanPham.getNsx() != null ? chiTietSanPham.getNsx().getTen() : null);
        chiTietSanPhamDto.setIdNsx(chiTietSanPham.getNsx() != null ? chiTietSanPham.getNsx().getId() : null);

        chiTietSanPhamDto.setKichCo(chiTietSanPham.getKichCo() != null ? chiTietSanPham.getKichCo().getTen() : null);
        chiTietSanPhamDto.setIdKichCo(chiTietSanPham.getKichCo() != null ? chiTietSanPham.getKichCo().getId() : null);

        chiTietSanPhamDto.setKieuDang(chiTietSanPham.getKieuDang() != null ? chiTietSanPham.getKieuDang().getTen() : null);
        chiTietSanPhamDto.setIdKieuDang(chiTietSanPham.getKieuDang() != null ? chiTietSanPham.getKieuDang().getId() : null);

        chiTietSanPhamDto.setMauSac(chiTietSanPham.getMauSac() != null ? chiTietSanPham.getMauSac().getTen() : null);
        chiTietSanPhamDto.setIdMauSac(chiTietSanPham.getMauSac() != null ? chiTietSanPham.getMauSac().getId() : null);

        chiTietSanPhamDto.setIdKhuyenMai(chiTietSanPham.getKhuyenMai() != null ? chiTietSanPham.getKhuyenMai().getId() : null);
        chiTietSanPhamDto.setThuongHieu(chiTietSanPham.getThuongHieu() != null ? chiTietSanPham.getThuongHieu().getTen() : null);
        chiTietSanPhamDto.setIdThuongHieu(chiTietSanPham.getThuongHieu() != null ? chiTietSanPham.getThuongHieu().getId() : null);


        return chiTietSanPhamDto;
    }


    public ChiTietSanPham mapToEntity(ChiTietSanPhamRequest dto) {
        ChiTietSanPham ctsp = new ChiTietSanPham();


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

        if (dto.getIdKhuyenMai() != null) {
            KhuyenMai khuyenMai = khuyenMaiService.findById(dto.getIdKhuyenMai());
            ctsp.setKhuyenMai(khuyenMai);
        }

        ThuongHieu thuongHieu = thuongHieuService.findById(dto.getIdThuongHieu());
        ctsp.setThuongHieu(thuongHieu);

        return ctsp;
    }

}
