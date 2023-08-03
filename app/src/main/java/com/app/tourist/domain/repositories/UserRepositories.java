package com.app.tourist.domain.repositories;

import com.app.tourist.core.utils.ApiResponse;
import com.app.tourist.domain.entities.User;

import java.util.List;

public interface UserRepositories {
    List<User> getAllUser();
}
