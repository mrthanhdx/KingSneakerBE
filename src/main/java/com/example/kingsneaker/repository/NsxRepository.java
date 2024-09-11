package com.example.kingsneaker.repository;

import com.example.kingsneaker.entity.NSX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NsxRepository extends JpaRepository<NSX,Long> {
}
