package com.example.bedemonosql.entity;


import com.example.bedemonosql.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "user")
public class User {

    @Id
    private String id;

    private String fullName;

    private String username;

    private String password;

    private String email;

    private String phone;

    private Integer gender;

    private String address;

    private String role;

    public User(UserDTO userDTO){
        BeanUtils.copyProperties(userDTO, this);
    }
}
