package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.service.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat-lieu")
public class ChatLieuController {
    @Autowired
    ChatLieuService chatLieuService;
}
