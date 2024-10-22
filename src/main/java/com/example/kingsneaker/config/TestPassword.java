package com.example.kingsneaker.config;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestPassword {
    public static void main(String[] args) {
        // Create a PasswordEncoder object using BCryptPasswordEncoder
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Example password to encode
        String rawPassword = "123";

        // Encode the password
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Print the encoded password
        System.out.println("Raw Password: " + rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);
    }
}
