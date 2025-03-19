package com.jolaar.personalwebsite.dto;

import com.jolaar.personalwebsite.model.AppUser;

public class UserDTO {
    private String fullName;
    private String email;

    public UserDTO(AppUser user) {
        this.fullName = user.getFirstName() + " " + user.getLastName();
        this.email = user.getEmail();
    }
}

