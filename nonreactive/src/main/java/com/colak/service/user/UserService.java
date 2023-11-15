package com.colak.service.user;

import com.colak.model.mongodb.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);
}
