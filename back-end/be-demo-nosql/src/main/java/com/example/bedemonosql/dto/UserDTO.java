package com.example.bedemonosql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {

    private String id;

    private String fullName;

    private String username;

    private String email;

    private String phone;

    private Integer gender;

    private String address;

    private String role;
}
