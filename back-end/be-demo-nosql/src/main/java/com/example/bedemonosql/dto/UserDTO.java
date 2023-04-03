package com.example.bedemonosql.dto;

import com.example.bedemonosql.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {

    private String id;

    private String fullName;

    private String password;

    private String username;

    private String email;

    private String phone;

    private Integer gender;

    private String address;

    private String role;

    public UserDTO(User user){
        BeanUtils.copyProperties(user, this);
    }

}
