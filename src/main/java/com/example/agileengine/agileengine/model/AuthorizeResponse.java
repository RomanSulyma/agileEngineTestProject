package com.example.agileengine.agileengine.model;

import lombok.Data;

@Data
public class AuthorizeResponse {
    private Boolean auth;
    private String token;
}
