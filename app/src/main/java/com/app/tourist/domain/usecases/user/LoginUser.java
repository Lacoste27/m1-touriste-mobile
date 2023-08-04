package com.app.tourist.domain.usecases.user;

import com.app.tourist.core.utils.Result;
import com.app.tourist.data.models.UserModel;
import com.app.tourist.data.repositories.UserRepositoryImpl;
import com.app.tourist.domain.repositories.UserRepositories;

import javax.inject.Inject;

public class LoginUser {

    private UserRepositoryImpl repository;

    public LoginUser(UserRepositoryImpl repositories){
        this.repository = repositories;
    }

    public Result<UserModel> login(String username, String password){
        try {
            UserModel loginresponse = (UserModel)this.repository.login(username, password);
            Result<UserModel> result = new Result.Success<UserModel>(loginresponse);
            return result;
        }catch (Exception exception) {
            return new Result.Error(exception);
        }
    }
}
