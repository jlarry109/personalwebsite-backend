package com.jolaar.personalwebsite.repository;

import com.jolaar.personalwebsite.common.enums.Role;
import com.jolaar.personalwebsite.model.Admin;
import com.jolaar.personalwebsite.model.AppUser;
import com.jolaar.personalwebsite.model.StandardUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
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
    public void UserRepositorySaveReturnsSavedUser() {
        // Act
        AppUser savedAdminUser = userRepository.save(adminUser);

        // Assert
        assertNotNull(savedAdminUser);
        assertEquals(adminUser, savedAdminUser);
    }

    @Test
    public void UserRepositorySaveAllReturnsSavedUsers() {
        // Act
        userRepository.save(adminUser);
        userRepository.save(standardUser);
        List<AppUser> savedAppUsers = userRepository.findAll();

        // Assert
        assertEquals(adminUser, savedAppUsers.getFirst());
        assertEquals(standardUser, savedAppUsers.getLast());
    }

    @Test
    void UserRepositoryFindByEmailReturnsUser() {
        // Act
        userRepository.save(standardUser);
        Optional<AppUser> optionalAppUser = userRepository.findByEmail(standardUser.getEmail());
        assertTrue(optionalAppUser.isPresent());
        AppUser appUser = optionalAppUser.get();
        assertEquals(standardUser.getEmail(), appUser.getEmail());
        assertEquals(standardUser.getId(), appUser.getId());
    }

    @Test
    void UserRepositoryFindByUsernameReturnsUser() {
        userRepository.save(standardUser);
        Optional<AppUser> optionalAppUser = userRepository.findByUsername(standardUser.getUsername());
        assertTrue(optionalAppUser.isPresent());
        AppUser appUser = optionalAppUser.get();
        assertEquals(standardUser.getUsername(), appUser.getUsername());
        assertEquals(standardUser.getId(), appUser.getId());
    }
}
