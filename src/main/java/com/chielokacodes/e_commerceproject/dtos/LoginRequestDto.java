package com.chielokacodes.e_commerceproject.dtos;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
    private String hashPassword;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
