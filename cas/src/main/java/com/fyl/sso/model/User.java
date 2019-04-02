package com.fyl.sso.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer expired;
    private Integer disabled;
}
