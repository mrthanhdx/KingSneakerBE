package com.example.kingsneaker.repository;

import com.example.kingsneaker.entity.CartItem;
import com.example.kingsneaker.entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu,Long> {
}
