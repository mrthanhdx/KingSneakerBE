package com.example.kingsneaker.mapper;

import com.example.kingsneaker.dto.SignUpDto;
import com.example.kingsneaker.dto.UserDto;
import com.example.kingsneaker.entity.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFullName(user.getHoTen());
        dto.setUsername(user.getTaiKhoan());
        dto.setRole(user.getRole().getTen());
        return dto;
    }

    @Mapping(target = "password", ignore = true)
    public User signUpToUser(SignUpDto userDto) {
        User user = new User();

        user.setSoDienThoai(userDto.getPhoneNumber());
        user.setHoTen(userDto.getFullName());
        user.setAddress(userDto.getAddress());
        user.setHoTen(userDto.getFullName());
        user.setNgaySinh(userDto.getDateOfBirth());
        user.setGioiTinh(userDto.getGender());
        user.setTaiKhoan(userDto.getUsername());
        user.setTrangThai("1");
        user.setEmail(userDto.getEmail());
        user.setNgayTao(LocalDate.now());
        System.out.println(user.getHoTen());

        return user;

    }
}
