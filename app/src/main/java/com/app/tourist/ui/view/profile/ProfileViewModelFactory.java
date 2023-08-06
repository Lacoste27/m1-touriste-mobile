package com.app.tourist.ui.view.profile;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.app.tourist.data.repositories.UserRepositoryImpl;
import com.app.tourist.data.sources.api.users.ApiUserSourceImpl;
import com.app.tourist.ui.view.login.LoginViewModel;

public class ProfileViewModelFactory implements ViewModelProvider.Factory {
    public Context context;

    public ProfileViewModelFactory(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
            return (T) new ProfileViewModel(this.context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
