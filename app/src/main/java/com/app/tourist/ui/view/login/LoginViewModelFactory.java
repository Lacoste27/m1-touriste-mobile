package com.app.tourist.ui.view.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.app.tourist.data.repositories.UserRepositoryImpl;
import com.app.tourist.data.sources.api.users.ApiUserSourceImpl;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    public Context context;

    public LoginViewModelFactory(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(UserRepositoryImpl.getInstance(new ApiUserSourceImpl()) ,this.context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
