package com.example.kingsneaker.repository;

import com.example.kingsneaker.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    @Modifying
    @Transactional
    @Query(value = "update san_pham set trang_thai = ?2 where id = ?1", nativeQuery = true)
    int updateStatusProduct(Long idSp, int status);
}
