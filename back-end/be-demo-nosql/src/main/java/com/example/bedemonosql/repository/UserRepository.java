package com.example.bedemonosql.repository;

import com.example.bedemonosql.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    User findByUsername(String username);

    boolean existsByUsername(String name);

    @Query("{'role': ?0}")
    Page<User> findUser(String role, Pageable pageable);

}
