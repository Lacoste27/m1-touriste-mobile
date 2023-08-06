package com.app.tourist.data.repositories;

import com.app.tourist.core.utils.ApiResponse;
import com.app.tourist.core.utils.Result;

import com.app.tourist.data.response.UserResponse;
import com.app.tourist.data.sources.api.users.ApiUserSourceImpl;

import com.app.tourist.domain.entities.User;
import com.app.tourist.domain.repositories.UserRepositories;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;
import java.util.SortedMap;


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

                LinkedTreeMap utilisateur = (LinkedTreeMap) data.getData();

                User result = new User();
                result.setNom(utilisateur.get("nom").toString());
                result.setPrenom(utilisateur.get("prenom").toString());
                result.setEmail(utilisateur.get("email").toString());

                return result;

            } else {
                throw ((Result.Error) response).getError();
            }
        }catch (Exception Exception){
            throw Exception;
        }
    }

    @Override
    public User signup(String nom, String prenom, String email, String password) throws Exception {
        try{
            Result<ApiResponse> response = this.apiUserSource.signup(nom, prenom, email, password);

            if(response instanceof Result.Success){
                ApiResponse data = ((Result.Success<ApiResponse>) response).getData();

                LinkedTreeMap utilisateur = (LinkedTreeMap) data.getData();

                User result = new User();
                result.setNom(utilisateur.get("nom").toString());
                result.setPrenom(utilisateur.get("prenom").toString());
                result.setEmail(utilisateur.get("email").toString());

                return result;

            } else {
                throw ((Result.Error) response).getError();
            }
        }catch (Exception Exception){
            throw Exception;
        }
    }


}
