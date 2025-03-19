package com.jolaar.personalwebsite.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("ADMIN") // This value will be stored in the `user_type` column for Admins
public class Admin extends AppUser {
}
