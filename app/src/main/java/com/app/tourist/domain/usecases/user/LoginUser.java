package com.app.tourist.domain.usecases.user;

import com.app.tourist.core.utils.Result;
import com.app.tourist.data.models.UserModel;
import com.app.tourist.data.repositories.UserRepositoryImpl;
import com.app.tourist.domain.entities.User;
import com.app.tourist.domain.repositories.UserRepositories;

import javax.inject.Inject;

public class LoginUser {

    private UserRepositoryImpl repository;

    public LoginUser(UserRepositoryImpl repositories){
        this.repository = repositories;
    }

    public Result<UserModel> login(String username, String password){
        try {
            User user = this.repository.login(username, password);
            if(user != null){
                UserModel loginresponse = new UserModel(user);
                Result<UserModel> result = new Result.Success<UserModel>(loginresponse);
                return result;
            } else{
                return new Result.Error(new Exception("Une erreur s'est produite"));
            }
        }catch (Exception exception) {
            return new Result.Error(exception);
        }
    }
}
