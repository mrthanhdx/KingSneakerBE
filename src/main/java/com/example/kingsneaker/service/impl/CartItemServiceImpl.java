package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.CartItem;
import com.example.kingsneaker.repository.CartItemRepository;
import com.example.kingsneaker.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;
    @Override
    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public CartItem findById(Long id) {
        return null;
    }

    @Override
    public List<CartItem> findAll() {
        return null;
    }

    @Override
    public List<CartItem> getListCartItemByIdCustomer(Long idCustomer) {
        return cartItemRepository.getListCartItemByIdCustomer(idCustomer);
    }



    @Override
    public void updateSoLuongSpCartItem(Long idCustomer, Integer soLuong,Long idCtsp) {
        cartItemRepository.updateSoLuongSpCartItem(idCustomer,soLuong,idCtsp);
    }
}
