package com.example.kingsneaker.repository;

import com.example.kingsneaker.entity.KieuDang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KieuDangRepository extends JpaRepository<KieuDang,Long> {
}
