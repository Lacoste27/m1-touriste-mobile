package com.app.tourist.domain.usecases.user;

import com.app.tourist.core.utils.Result;
import com.app.tourist.data.models.UserModel;
import com.app.tourist.data.repositories.UserRepositoryImpl;
import com.app.tourist.domain.entities.User;

public class SignupUser {

    private UserRepositoryImpl repository;

    public SignupUser(UserRepositoryImpl repositories){
        this.repository = repositories;
    }

    public Result<UserModel> signup(String nom, String prenom, String username, String password){
        try {

            User user = this.repository.signup(nom, prenom, username, password);
            if(user != null){
                UserModel signupResponse = new UserModel(user);

                Result<UserModel> result = new Result.Success<UserModel>(signupResponse);
                return result;
            } else{
                return new Result.Error(new Exception("Une erreur s'est produite"));
            }
        }catch (Exception exception) {
            return new Result.Error(exception);
        }
    }
}
