package com.app.tourist.ui.view.profile;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.tourist.data.repositories.TokenRepositoryImpl;
import com.app.tourist.data.repositories.UserRepositoryImpl;
import com.app.tourist.data.sources.local.TokenDataSource;
import com.app.tourist.domain.usecases.user.LoginUser;

public class ProfileViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private TokenRepositoryImpl tokenRepository;
    private Context context;

    public ProfileViewModel(Context context){

        this.context = context;
    }

    public ProfileViewModel() {
    }

    public void logout(){
        TokenDataSource tokenDataSource = new TokenDataSource(this.context);
        this.tokenRepository = TokenRepositoryImpl.getInstance(tokenDataSource);
        this.tokenRepository.clear();
    }
}