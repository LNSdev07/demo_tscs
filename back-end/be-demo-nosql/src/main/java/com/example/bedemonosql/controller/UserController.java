package com.example.bedemonosql.controller;


import com.example.bedemonosql.dto.request.Paginator;
import com.example.bedemonosql.dto.response.BaseResponse;
import com.example.bedemonosql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/user/")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority(T(com.example.bedemonosql.utils.Constant).ROLE_ADMIN)")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {

    private final UserService service;

    @PostMapping("find-user")
    public BaseResponse<?> findUser(@Valid @RequestBody Paginator paginator){
        return service.findUser(paginator);
    }
}
