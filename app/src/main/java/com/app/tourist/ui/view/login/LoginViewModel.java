package com.app.tourist.ui.view.login;

import android.content.SharedPreferences;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.tourist.core.utils.Result;
import com.app.tourist.data.models.UserModel;
import com.app.tourist.data.repositories.TokenRepositoryImpl;
import com.app.tourist.data.repositories.UserRepositoryImpl;
import com.app.tourist.data.sources.api.users.ApiUserSourceImpl;
import com.app.tourist.data.sources.local.TokenDataSource;
import com.app.tourist.domain.repositories.UserRepositories;
import com.app.tourist.domain.usecases.user.LoginUser;
import com.app.tourist.R;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<LoginState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private TokenRepositoryImpl tokenRepository;
    private LoginUser loginUseCase;

    public LoginViewModel(){
        ApiUserSourceImpl datasource = new ApiUserSourceImpl();
        UserRepositoryImpl repositories = new UserRepositoryImpl(datasource);

        this.loginUseCase = new LoginUser(repositories);
    }

    public LoginViewModel(UserRepositoryImpl repository){
        this.loginUseCase = new LoginUser(repository);
    }

    LiveData<LoginState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        try{
            Result<UserModel> result = this.loginUseCase.login(username, password);
            if (result instanceof Result.Success) {
                UserModel user = (UserModel) ((Result.Success<UserModel>) result).getData();
                LoggedInUserView data = new LoggedInUserView(user.getNom());
                loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
            } else {
                String message = ((Result.Error) result).getError().getLocalizedMessage().split(":")[1];
                loginResult.setValue(new LoginResult(message));
            }
        }catch (Exception exception){
            String message = exception.getLocalizedMessage().split(":")[1];
            loginResult.setValue(new LoginResult(message));
        }
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

    public void clearToken(SharedPreferences preferences){
        TokenDataSource tokenDataSource = new TokenDataSource(preferences);
        this.tokenRepository = TokenRepositoryImpl.getInstance(tokenDataSource);
        this.tokenRepository.clearToken();
    }
    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginState(true));
        }
    }

    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}