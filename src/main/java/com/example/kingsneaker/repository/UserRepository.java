package com.example.kingsneaker.repository;

import com.example.kingsneaker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select * from users where id_role = 2",nativeQuery = true)
    List<User> getAllCustomer();
}
