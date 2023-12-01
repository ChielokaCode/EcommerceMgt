package com.chielokacodes.e_commerceproject.model;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.chielokacodes.e_commerceproject.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String gender;
    private String password;
    private BigDecimal balance;
    private Role role;

    public User(UserDto signedUpUser) {
       // this.firstname = signedUpUser.getFirstname();
       // this.lastname = signedUpUser.getUsername();
        this.username = signedUpUser.getUsername();
        this.email = signedUpUser.getEmail();
        //this.gender = signedUpUser.getGender();
        this.password = BCrypt.withDefaults().hashToString(12, signedUpUser.getPassword().toCharArray());
    }
}
