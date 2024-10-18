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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login (
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        CredentialsDto credentialsDto = new CredentialsDto();
        credentialsDto.setUsername(username);
        credentialsDto.setPassword(password);

        UserDto user = userService.login(credentialsDto);

        // Generate the token and set the expiration date
        String token = userAuthProvider.createToken(user.getUsername());
        Date tokenExpiry = userAuthProvider.getTokenExpiry(token); // Extract the expiry date

        user.setToken(token);
        user.setTokenExpiry(tokenExpiry); // Set the expiration date in UserDto

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
