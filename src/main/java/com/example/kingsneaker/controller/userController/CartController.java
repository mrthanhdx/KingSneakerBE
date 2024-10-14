package com.example.kingsneaker.controller.userController;

import com.example.kingsneaker.entity.CartItem;
import com.example.kingsneaker.entity.ChiTietSanPham;
import com.example.kingsneaker.entity.User;
import com.example.kingsneaker.service.CartItemService;
import com.example.kingsneaker.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user/api/v1/cart")
public class CartController {

    @Autowired
    CartItemService cartItemService;

    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;


    @GetMapping("/show-all/{idCustomer}")
    public ResponseEntity<?> showAllCartItem(@PathVariable("idCustomer") Long idCustomer) {
        return ResponseEntity.ok(cartItemService.getListCartItemByIdCustomer(idCustomer));
    }

    @PostMapping("/new-cart-item")
    public ResponseEntity<?> createNewCartItem(@RequestParam("idCustomer") Long idCustomer,
                                               @RequestParam("idCtsp") Long idCtsp,
                                               @RequestParam("quantity") Integer quantity) {
        // get data customer and productDetail to set to cartItem
        User customer = new User();
        customer.setId(idCustomer);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.findById(idCtsp);
        if (chiTietSanPham.getSoLuong() < quantity) {
            return new ResponseEntity<>("So Luong san pham khong du ( con " + chiTietSanPham.getSoLuong() + " san pham)", HttpStatus.BAD_REQUEST);
        }
        CartItem cartItem = new CartItem();
        cartItem.setCustomer(customer);
        cartItem.setChiTietSanPham(chiTietSanPham);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(chiTietSanPham.getGiaBan());
        List<CartItem> listCartItem = cartItemService.getListCartItemByIdCustomer(idCustomer);
        for (CartItem c : listCartItem) {
            if (cartItem.getCustomer().getId() == c.getCustomer().getId() && cartItem.getChiTietSanPham().getId() == c.getChiTietSanPham().getId()) {
                Integer soLuongMoi = c.getQuantity() + quantity;
                cartItemService.updateSoLuongSpCartItem(idCustomer, soLuongMoi, idCtsp);
                return ResponseEntity.ok("Thêm sản phẩm thành công");
            }
        }
        cartItemService.save(cartItem);
        return ResponseEntity.ok("Thêm sản phẩm thành công");
    }
}
