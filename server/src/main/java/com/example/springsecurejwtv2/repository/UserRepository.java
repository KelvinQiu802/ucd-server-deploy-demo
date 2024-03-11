package com.example.springsecurejwtv2.repository;

import com.example.springsecurejwtv2.model.UserEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<UserEntity, String> {
}
