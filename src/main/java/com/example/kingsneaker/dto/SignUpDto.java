package com.example.kingsneaker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class SignUpDto {
    private String fullName;

    private String address;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private Integer gender;

    private String username;

    private char[] password;

    private String email;
}
