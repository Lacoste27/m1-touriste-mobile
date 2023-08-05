package com.app.tourist.ui.view.signup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.app.tourist.data.repositories.UserRepositoryImpl;
import com.app.tourist.data.sources.api.users.ApiUserSourceImpl;
import com.app.tourist.ui.view.login.LoginViewModel;

public class SignupViewModelFactory implements ViewModelProvider.Factory{
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SignupViewModel.class)) {
            return (T) new SignupViewModel(UserRepositoryImpl.getInstance(new ApiUserSourceImpl()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
