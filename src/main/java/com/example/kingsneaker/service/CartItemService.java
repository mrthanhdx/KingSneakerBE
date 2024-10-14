package com.example.kingsneaker.service;

import com.example.kingsneaker.entity.CartItem;

import java.util.List;

public interface CartItemService extends CommonService<CartItem> {
     List<CartItem> getListCartItemByIdCustomer(Long idCustomer);

     void updateSoLuongSpCartItem(Long idCustomer,Integer soLuong,Long idCtsp);

}
