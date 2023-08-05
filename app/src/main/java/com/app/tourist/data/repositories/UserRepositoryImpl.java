package com.app.tourist.data.repositories;

import com.app.tourist.core.error.BadRequestException;
import com.app.tourist.core.service.network.NetworkGetCall;
import com.app.tourist.core.service.network.NetworkPostCall;
import com.app.tourist.core.utils.ApiResponse;
import com.app.tourist.core.utils.Result;

import com.app.tourist.data.response.UserResponse;
import com.app.tourist.data.sources.api.users.ApiUserSourceImpl;

import com.app.tourist.domain.entities.User;
import com.app.tourist.domain.repositories.UserRepositories;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;


public class UserRepositoryImpl implements UserRepositories {

    private static volatile UserRepositoryImpl instance;
    private ApiUserSourceImpl apiUserSource;


    public UserRepositoryImpl(ApiUserSourceImpl apiUserSource){
        this.apiUserSource = apiUserSource;
    }

    public static UserRepositoryImpl getInstance(ApiUserSourceImpl dataSource) {
        if (instance == null) {
            instance = new UserRepositoryImpl(dataSource);
        }
        return instance;
    }

    public ApiUserSourceImpl getApiUserSource() {
        return apiUserSource;
    }

    @Override
    public List<User> getAllUser() {
       try{
           Result<ApiResponse> response = this.apiUserSource.getAllUser();

           return null;
       }catch (Exception Exception){
           throw Exception;
       }
    }

    @Override
    public User login(String username, String password) throws Exception {
        try{
            Result<ApiResponse> response = this.apiUserSource.login(username, password);

            if(response instanceof Result.Success){
                ApiResponse data = ((Result.Success<ApiResponse>) response).getData();

                UserResponse utilisateur = (UserResponse) data.getData();

                User result = new User();
                result.setEmail(utilisateur.getEmail());
                result.setNom(utilisateur.getNom());
                result.setEmail(utilisateur.getEmail());

                return result;

            } else {
                throw ((Result.Error) response).getError();
            }
        }catch (Exception Exception){
            throw Exception;
        }
    }


}
