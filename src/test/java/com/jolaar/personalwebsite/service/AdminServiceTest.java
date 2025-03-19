package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.enums.Role;
import com.jolaar.personalwebsite.dto.AuthRequest;
import com.jolaar.personalwebsite.model.Admin;
import com.jolaar.personalwebsite.model.AppUser;
import com.jolaar.personalwebsite.model.StandardUser;
import com.jolaar.personalwebsite.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminService adminService;

    private AppUser adminUser;
    private AppUser standardUser;

    @BeforeEach
    public void setUp() {
        adminUser = new Admin();
        adminUser.setUsername("adminUser");
        adminUser.setPassword("adminPassword123");
        adminUser.setRole(Role.ADMIN);
        adminUser.setFirstName("Admin");
        adminUser.setLastName("User");
        adminUser.setEmail("admin@example.com");

        standardUser = new StandardUser();
        standardUser.setUsername("standardUser");
        standardUser.setPassword("userPassword123");
        standardUser.setRole(Role.STANDARD_USER);
        standardUser.setFirstName("John");
        standardUser.setLastName("Doe");
        standardUser.setEmail("user@example.com");
    }

    @Test
    public void AdminServiceFindByUsernameReturnsUser(){
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(standardUser));

        Optional<AppUser> foundUser = adminService.findByUsername(standardUser.getUsername());
        assertTrue(foundUser.isPresent());
        assertEquals(standardUser, foundUser.get());
    }

    @Test
    public void AdminServiceFindByEmailReturnsUser(){
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(adminUser));

        Optional<AppUser> foundUser = adminService.findByEmail(adminUser.getEmail());
        assertTrue(foundUser.isPresent());
        assertEquals(adminUser, foundUser.get());
    }

    @Test
    public void AdminServiceRegisterStandardUserReturnsStandardUser(){
        when(userRepository.save(any(AppUser.class))).thenReturn(standardUser);

        AppUser registeredUser = adminService.registerStandardUser(new AuthRequest());
        assertNotNull(registeredUser);
        assertEquals(Role.STANDARD_USER, registeredUser.getRole());
        assertEquals(standardUser, registeredUser);
    }

    @Test
    public void AdminServiceRegisterAdminReturnsAdmin(){
        when(userRepository.save(any(AppUser.class))).thenReturn(adminUser);

        AppUser registeredAdmin = adminService.registerAdmin(new AuthRequest());
        assertNotNull(registeredAdmin);
        assertEquals(Role.ADMIN, registeredAdmin.getRole());
        assertEquals(adminUser, registeredAdmin);
    }

    @Test
    public void AdminServiceValidatePasswordWithInvalidEmailReturnsFalse(){
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertFalse(adminService.validatePassword(anyString(), anyString()));
    }

    @Test
    public void AdminServiceValidatePasswordWithInvalidPasswordReturnsFalse(){
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertFalse(adminService.validatePassword("valid-email", "invalid-password"));
    }

    @Test
    public void AdminServiceValidatePasswordWithValidEmailReturnsTrue(){
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        assertTrue(adminService.validatePassword(anyString(), anyString()));
    }
}
