package com.example.kingsneaker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class UserDto {
    private Long id;
    private String fullName;
    private String username;
    private String token;

    private String role;

    private String email;

    private String phoneNumber;

    private Integer gender;

    private String address;
    private Date tokenExpiry;
}
