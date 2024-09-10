package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chi-tiet-san-pham")
public class ChiTietSanPhamController {

    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

}
