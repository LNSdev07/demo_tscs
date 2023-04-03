package com.example.bedemonosql.service.impl;


import com.example.bedemonosql.dto.UserDTO;
import com.example.bedemonosql.dto.request.Paginator;
import com.example.bedemonosql.dto.response.BaseResponse;
import com.example.bedemonosql.entity.User;
import com.example.bedemonosql.repository.UserRepository;
import com.example.bedemonosql.service.UserService;
import com.example.bedemonosql.utils.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public BaseResponse<?> findUser(Paginator paginator) {

        try{
            Pageable pageable = PageRequest.of(paginator.getPage()-1, paginator.getPageSize(),
                    Sort.Direction.fromString(paginator.getCondition().equals("asc")?"asc":"desc"),
                   (paginator.getSortColumn().isEmpty() || paginator.getSortColumn().equals(""))?
                    "username": paginator.getSortColumn());

            Page<User> page = repository.findUser(Constant.ROLE_USER, pageable);

            if(page == null){
                return BaseResponse.builder().status(HttpStatus.BAD_REQUEST.value()).build();
            }

            List<UserDTO> res = page.getContent().stream().map(UserDTO::new).collect(Collectors.toList());

            return BaseResponse.builder().data(res).totalRecords(page.getTotalElements())
                    .message("success").status(HttpStatus.OK.value()).build();

        }catch (Exception e){
            log.error(e.getMessage());
            return BaseResponse.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }
}
