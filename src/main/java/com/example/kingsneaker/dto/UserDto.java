package com.example.kingsneaker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
