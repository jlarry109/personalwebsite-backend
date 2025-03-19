package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.model.Admin;
import com.jolaar.personalwebsite.model.AppUser;
import com.jolaar.personalwebsite.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminUserDetailsServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminUserDetailsService adminUserDetailsService;

    @BeforeEach
    public void setUp(){}

    @Test
    public void AdminUserDetailsServiceLoadUserByUsernameWithInvalidEmailThrowsException(){
        doThrow(UsernameNotFoundException.class).when(userRepository).findByEmail(anyString());

        assertThrows(UsernameNotFoundException.class, () -> adminUserDetailsService.loadUserByUsername("dummy-email"));

        verify(userRepository, times(1)).findByEmail("dummy-email");
    }

    @Test
    public void AdminUserDetailsServiceLoadUserByUsernameWithValidEmailReturnsUser(){
        UserDetails expectedUserDetails = User.builder()
                .username("valid-email")
                .password("valid-password")
                .roles("ADMIN")
                .build();
        AppUser user = new Admin();
        user.setEmail("valid-email");
        user.setPassword("valid-password");
        doReturn(Optional.ofNullable(user)).when(userRepository).findByEmail(anyString());

        UserDetails returnedUserDetails = adminUserDetailsService.loadUserByUsername(user.getEmail());
        assertNotNull(returnedUserDetails);
        assertEquals(expectedUserDetails, returnedUserDetails);

        verify(userRepository, times(1)).findByEmail(user.getEmail());
    }
}
