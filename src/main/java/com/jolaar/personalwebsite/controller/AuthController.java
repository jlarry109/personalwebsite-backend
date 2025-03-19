package com.jolaar.personalwebsite.controller;

import com.jolaar.personalwebsite.common.security.JwtUtil;
import com.jolaar.personalwebsite.model.AppUser;
import com.jolaar.personalwebsite.service.AdminService;
import com.jolaar.personalwebsite.dto.AuthRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    public AuthController(AdminService adminService, JwtUtil jwtUtil) {
        this.adminService = adminService;
        this.jwtUtil = jwtUtil;
    }
/* Only registered Admins should be able to register other admins. This has been done in AdminController*/

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        AppUser standardUser = adminService.registerStandardUser(request);
        return ResponseEntity.ok("User registered: " + standardUser.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Optional<AppUser> user = adminService.findByEmail(request.getEmail());
        if (user.isPresent() && adminService.validatePassword(request.getPassword(), user.get().getPassword())) {
            String token = jwtUtil.generateToken(request.getEmail());
            String role = user.get().getRole().name(); // Get user's role
            return ResponseEntity.ok(Map.of("token", token, "role", role));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = jwtUtil.extractTokenFromRequest(request);
        if (token != null) {
            jwtUtil.blacklistToken(token); // Implement this in JwtUtil
        }
        return ResponseEntity.ok("Logged out successfully");
    }

}
