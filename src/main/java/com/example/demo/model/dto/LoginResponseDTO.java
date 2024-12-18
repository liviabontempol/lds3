package com.example.demo.model.dto;

import com.example.demo.model.User;

public record LoginResponseDTO(
    int id,
    String userType,
    User user
) {}
