package com.example.kingsneaker.service.impl;

import com.example.kingsneaker.dto.CredentialsDto;
import com.example.kingsneaker.dto.SignUpDto;
import com.example.kingsneaker.dto.UserDto;
import com.example.kingsneaker.entity.Role;
import com.example.kingsneaker.entity.User;
import com.example.kingsneaker.exception.AppException;
import com.example.kingsneaker.mapper.UserMapper;
import com.example.kingsneaker.repository.UserRepository;
import com.example.kingsneaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllCustomer() {
        return userRepository.getAllCustomer();
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new AppException("unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByUserName(credentialsDto.getUsername())
                .orElseThrow(() -> new AppException("Unknow User", HttpStatus.NOT_FOUND));
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getMatKhau())) {
            return userMapper.toDto(user);
        }
        throw new AppException("incorrect password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByUserName(userDto.getUsername());
        if (optionalUser.isPresent()) {
            throw new AppException("username has already exist", HttpStatus.BAD_REQUEST);
        }
        User user = userMapper.signUpToUser(userDto);
        user.setMatKhau(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));
        Role role = new Role();
        role.setId(Long.valueOf(2));
        user.setRole(role);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
