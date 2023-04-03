package com.example.bedemonosql.service;

import com.example.bedemonosql.dto.UserDTO;
import com.example.bedemonosql.dto.request.AdminDTO;
import com.example.bedemonosql.dto.response.BaseResponse;

public interface AuthService {

    BaseResponse<?> login(AdminDTO dto);

    BaseResponse<?> register(UserDTO userDTO);
}
