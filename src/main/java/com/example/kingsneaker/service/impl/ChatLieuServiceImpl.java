package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.entity.ChatLieu;
import com.example.kingsneaker.repository.ChatLieuRepository;
import com.example.kingsneaker.service.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatLieuServiceImpl implements ChatLieuService {
    @Autowired
    ChatLieuRepository chatLieuRepository;
    @Override
    public void save(ChatLieu chatLieu) {
        chatLieuRepository.save(chatLieu);
    }

    @Override
    public void deleteById(Long id) {
        chatLieuRepository.deleteById(id);
    }

    @Override
    public ChatLieu findById(Long id) {
        return chatLieuRepository.findById(id).orElse(null);
    }

    @Override
    public List<ChatLieu> findAll() {
        return chatLieuRepository.findAll();
    }
}
