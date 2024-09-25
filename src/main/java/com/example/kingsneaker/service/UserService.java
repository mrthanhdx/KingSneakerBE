package com.example.kingsneaker.service;

import com.example.kingsneaker.entity.User;

import java.util.List;

public interface UserService extends CommonService<User> {

    List<User> getAllCustomer();
}
