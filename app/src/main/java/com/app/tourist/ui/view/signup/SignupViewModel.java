package com.app.tourist.ui.view.signup;

import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.app.tourist.data.repositories.TokenRepositoryImpl;
import com.app.tourist.data.repositories.UserRepositoryImpl;
import com.app.tourist.data.sources.api.users.ApiUserSourceImpl;
import com.app.tourist.data.sources.local.TokenDataSource;
import com.app.tourist.domain.usecases.user.LoginUser;

public class SignupViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private TokenRepositoryImpl tokenRepository;
    private LoginUser loginUseCase;

    public SignupViewModel(){
    }

    public void setToken(SharedPreferences preferences, String token){
        TokenDataSource tokenDataSource = new TokenDataSource(preferences);
        this.tokenRepository = TokenRepositoryImpl.getInstance(tokenDataSource);
        this.tokenRepository.saveToken(token);
    }

    public String getToken(SharedPreferences preferences){
        TokenDataSource tokenDataSource = new TokenDataSource(preferences);
        this.tokenRepository = TokenRepositoryImpl.getInstance(tokenDataSource);
        return this.tokenRepository.getToken();
    }
}