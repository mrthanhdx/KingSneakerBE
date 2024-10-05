package com.example.kingsneaker.service;

import com.example.kingsneaker.dto.CredentialsDto;
import com.example.kingsneaker.dto.SignUpDto;
import com.example.kingsneaker.dto.UserDto;
import com.example.kingsneaker.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends CommonService<User> {

    List<User> getAllCustomer();

   public UserDto findByUsername(String username);

   public UserDto login(CredentialsDto credentialsDto);

    public UserDto register(SignUpDto userDto);
}
