package com.example.bedemonosql.service.impl;

import com.example.bedemonosql.dto.UserDTO;
import com.example.bedemonosql.dto.request.AdminDTO;
import com.example.bedemonosql.dto.response.BaseResponse;
import com.example.bedemonosql.dto.response.LoginResponse;
import com.example.bedemonosql.entity.User;
import com.example.bedemonosql.repository.UserRepository;
import com.example.bedemonosql.security.jwt.JwtProvider;
import com.example.bedemonosql.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtProvider jwtProvider;

    private final RedisTemplate redisTemplate;

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Value("${redis.expired}")
    private Long timeExpired;

    @Override
    public BaseResponse<?> login(AdminDTO dto) {

        try {
            User user = repository.findByUsername(dto.getUsername());

            if(user == null){
                log.error("USER NOT FOUND!");
                return BaseResponse.builder().message("user not found").status(HttpStatus.BAD_REQUEST.value())
                        .build();
            }

            Object token = redisTemplate.opsForValue().get(user.getUsername());
            if(token == null){
                token = jwtProvider.createToken(user);
                redisTemplate.opsForValue().set(user.getUsername(), token);
                redisTemplate.expire(user.getUsername(), timeExpired, TimeUnit.SECONDS);
            }
            LoginResponse response = LoginResponse.builder().token(token.toString())
                    .username(user.getUsername()).fullName(user.getFullName()).role(user.getRole()).build();

            return BaseResponse.builder().data(response).message("login success").status(HttpStatus.OK.value())
                    .build();
        }catch (Exception e){
            log.error(e.getMessage());
            return BaseResponse.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public BaseResponse<?> register(UserDTO userDTO) {
        try{
            if(repository.existsByUsername(userDTO.getUsername())){
            log.error("user existed");
            return BaseResponse.builder().message("user existed").status(HttpStatus.BAD_REQUEST.value())
                    .build();
            }
            User user = new User(userDTO);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            repository.save(user);
            return BaseResponse.builder().message("register success").data(repository.findByUsername(user.getUsername()))
                    .status(HttpStatus.OK.value()).build();
        }catch (Exception e){
            log.error(e.getMessage());
            return BaseResponse.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }
}
