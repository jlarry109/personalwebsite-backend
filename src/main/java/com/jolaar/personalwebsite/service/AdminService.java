package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.enums.Role;
import com.jolaar.personalwebsite.dto.AuthRequest;
import com.jolaar.personalwebsite.model.Admin;
import com.jolaar.personalwebsite.model.AppUser;
import com.jolaar.personalwebsite.model.StandardUser;
import com.jolaar.personalwebsite.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(UserRepository adminRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<AppUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<AppUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public AppUser registerStandardUser(AuthRequest authRequest) {
        AppUser standardUser = new StandardUser();
        standardUser.setEmail(authRequest.getEmail());
        standardUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        standardUser.setRole(Role.STANDARD_USER);
        standardUser.setUsername(authRequest.getUsername());
        standardUser.setFirstName(authRequest.getFirstName());
        standardUser.setLastName(authRequest.getLastName());
        return userRepository.save(standardUser);
    }
    public Admin registerAdmin(AuthRequest authRequest) {
        Admin admin = new Admin();
        admin.setEmail(authRequest.getEmail());
        admin.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        admin.setRole(Role.ADMIN);
        admin.setUsername(authRequest.getUsername());
        admin.setFirstName(authRequest.getFirstName());
        admin.setLastName(admin.getLastName());
        return userRepository.save(admin);
    }

    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


}
