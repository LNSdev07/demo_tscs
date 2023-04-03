package com.example.bedemonosql.service;

import com.example.bedemonosql.dto.request.Paginator;
import com.example.bedemonosql.dto.response.BaseResponse;

public interface UserService {

    BaseResponse<?> findUser(Paginator paginator);
}
