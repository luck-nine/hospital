package com.ccunix.hospital.security.domain.model;

import lombok.Data;

@Data
public class LoginBody {
    private String username;
    private String password;
}
