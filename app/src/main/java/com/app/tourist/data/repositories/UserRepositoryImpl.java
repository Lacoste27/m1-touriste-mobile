package com.app.tourist.data.repositories;

import com.app.tourist.core.utils.ApiResponse;
import com.app.tourist.core.utils.Result;

import com.app.tourist.data.sources.api.users.ApiUserSourceImpl;

import com.app.tourist.domain.entities.User;
import com.app.tourist.domain.repositories.UserRepositories;

import java.util.List;

public class UserRepositoryImpl implements UserRepositories {
    private ApiUserSourceImpl apiUserSource;

    public UserRepositoryImpl(ApiUserSourceImpl apiUserSource){
        this.apiUserSource = apiUserSource;
    }

    public ApiUserSourceImpl getApiUserSource() {
        return apiUserSource;
    }

    @Override
    public List<User> getAllUser() throws  Exception{
       try{
           Result<ApiResponse> response = this.apiUserSource.getAllUser();
           return null;
       }catch (Exception Exception){
           throw Exception;
       }
    }
}
