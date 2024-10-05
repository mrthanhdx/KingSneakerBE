package com.example.kingsneaker.controller.authController;

import com.example.kingsneaker.config.UserAuthProvider;
import com.example.kingsneaker.dto.CredentialsDto;
import com.example.kingsneaker.dto.SignUpDto;
import com.example.kingsneaker.dto.UserDto;
import com.example.kingsneaker.entity.User;
import com.example.kingsneaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login (@RequestBody CredentialsDto credentialsDto){
        UserDto user = userService.login(credentialsDto);
        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto){
        System.out.println(signUpDto.toString());
        UserDto dto = userService.register(signUpDto);
        dto.setToken(userAuthProvider.createToken(signUpDto.getUsername()));

        return ResponseEntity.created(URI.create("/users/"+dto.getId()))
                .body(dto);
    }
}
