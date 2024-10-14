package com.example.kingsneaker.repository;

import com.example.kingsneaker.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Query(value = "select * from cart_item where id_customer = ?1",nativeQuery = true)
    List<CartItem> getListCartItemByIdCustomer(Long idCustomer);

    @Modifying
    @Transactional
    @Query(value = "update  cart_item set so_luong = ?2 where id_customer = ?1 and id_chi_tiet_san_pham=?3",nativeQuery = true)
    void updateSoLuongSpCartItem(Long idCustomer,Integer soLuong,Long idCtsp);
}
