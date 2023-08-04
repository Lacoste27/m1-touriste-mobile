package com.app.tourist.ui.view.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.tourist.core.utils.Result;
import com.app.tourist.data.models.UserModel;
import com.app.tourist.data.repositories.UserRepositoryImpl;
import com.app.tourist.data.sources.api.users.ApiUserSourceImpl;
import com.app.tourist.domain.repositories.UserRepositories;
import com.app.tourist.domain.usecases.user.LoginUser;
import com.app.tourist.R;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<LoginState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();

    private LoginUser loginUseCase;

    public LoginViewModel(){
        ApiUserSourceImpl datasource = new ApiUserSourceImpl();
        UserRepositoryImpl repositories = new UserRepositoryImpl(datasource);
        this.loginUseCase = new LoginUser(repositories);
    }

    LiveData<LoginState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        Result<UserModel> result = this.loginUseCase.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUserView data = ((Result.Success<LoggedInUserView>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }
}