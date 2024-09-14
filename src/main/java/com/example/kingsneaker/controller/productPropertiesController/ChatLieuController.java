package com.example.kingsneaker.controller.productPropertiesController;

import com.example.kingsneaker.entity.ChatLieu;
import com.example.kingsneaker.service.ChatLieuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat-lieu")
@CrossOrigin(origins = "*",methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST ,RequestMethod.DELETE})

public class ChatLieuController {
    @Autowired
    ChatLieuService service;

    @GetMapping("/show-all")
    public ResponseEntity<List<ChatLieu>> showListChatLieu(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/new-chat-lieu")
    public ResponseEntity<ChatLieu> newChatLieu(@RequestBody @Valid ChatLieu chatLieu){
        service.save(chatLieu);
        return new ResponseEntity<>(chatLieu,HttpStatus.OK);
    }

    @PutMapping("/update-chat-lieu/{idChatLieu}")
    public ResponseEntity<ChatLieu> updateChatLieu(@RequestBody @Valid ChatLieu chatLieu,@PathVariable("idChatLieu") Long idChatLieu){
        chatLieu.setId(idChatLieu);
        service.save(chatLieu);
        return ResponseEntity.ok(chatLieu);
    }

    @DeleteMapping("/delete-chat-lieu/{idChatLieu}")
    public ResponseEntity<ChatLieu> deleteChatLieu(@PathVariable("idChatLieu") Long idChatLieu) {
        ChatLieu chatLieu =  service.findById(idChatLieu);
        service.deleteById(idChatLieu);
        return ResponseEntity.ok(chatLieu);
    }
}
