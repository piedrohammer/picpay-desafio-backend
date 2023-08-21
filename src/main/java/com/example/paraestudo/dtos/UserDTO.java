package com.example.paraestudo.dtos;

import com.example.paraestudo.enums.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, UserType userType, String email, String password) {
}
