package com.chielokacodes.e_commerceproject.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {
    private Long id;
    private String productIds;
    private BigDecimal totalPrice;
}
