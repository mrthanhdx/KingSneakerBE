package com.example.kingsneaker.repository;

import com.example.kingsneaker.dto.UserDto;
import com.example.kingsneaker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users where id_role = 2", nativeQuery = true)
    List<User> getAllCustomer();

    @Query(value = "select * from users where tai_khoan = ?1",nativeQuery = true)
    Optional<User> findByUserName(String username);
}
