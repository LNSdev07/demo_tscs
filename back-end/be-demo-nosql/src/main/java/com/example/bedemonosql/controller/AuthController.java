package com.example.bedemonosql.controller;

import com.example.bedemonosql.dto.UserDTO;
import com.example.bedemonosql.dto.request.AdminDTO;
import com.example.bedemonosql.dto.response.BaseResponse;
import com.example.bedemonosql.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public BaseResponse<?> login(@Valid @RequestBody AdminDTO dto){
        return service.login(dto);
    }

    @PostMapping("/register")
    public BaseResponse<?> register(@Valid @RequestBody UserDTO userDTO){
        return service.register(userDTO);
    }


}
