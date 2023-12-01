package com.chielokacodes.e_commerceproject.config;

import lombok.Getter;

@Getter
public class DatabaseConfiguration {
    private final String DB_URL = "jdbc:mysql://localhost:3306/ecommerces";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "DivineMercy123";
}