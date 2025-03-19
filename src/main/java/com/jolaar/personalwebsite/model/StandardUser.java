package com.jolaar.personalwebsite.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("STANDARD_USER")
public class StandardUser extends AppUser{
}
