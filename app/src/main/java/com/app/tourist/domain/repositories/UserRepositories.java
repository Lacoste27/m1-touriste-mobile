package com.app.tourist.domain.repositories;

import com.app.tourist.core.utils.ApiResponse;
import com.app.tourist.domain.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserRepositories {
    List<User> getAllUser();
    User login(String username, String password) throws Exception;
    User signup(String nom, String prenom, String email, String password)  throws Exception;
}
