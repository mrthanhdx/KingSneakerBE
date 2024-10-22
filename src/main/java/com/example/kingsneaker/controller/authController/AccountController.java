package com.example.kingsneaker.controller.authController;

import com.example.kingsneaker.dto.UserDto;
import com.example.kingsneaker.entity.User;
import com.example.kingsneaker.mapper.UserMapper;
import com.example.kingsneaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/api/v1")
public class AccountController {
    @Autowired
    UserService userService;


    @PutMapping("/update-infomation/{idUser}")
    public ResponseEntity<?> updateInfoUser(@RequestBody UserDto userDto, @PathVariable("idUser") Long idUser) {
        // Fetch the existing user
        User existingUser = userService.findById(idUser);
        if (existingUser == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        // Only update fields that are not null or blank in userDto
        if (userDto.getFullName() != null) {
            existingUser.setHoTen(userDto.getFullName());
        }

        if (userDto.getPhoneNumber() != null) {
            existingUser.setSoDienThoai(userDto.getPhoneNumber());
        }
        if (userDto.getEmail() != null) {
            existingUser.setEmail(userDto.getEmail());
        }
        if (userDto.getAddress() != null) {
            existingUser.setAddress(userDto.getAddress());
        }

        // Optionally handle other fields similarly if needed
        // Save the updated user
        userService.save(existingUser);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Update Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
