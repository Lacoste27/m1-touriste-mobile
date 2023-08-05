package com.app.tourist.ui.view.signup;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.tourist.core.utils.Result;
import com.app.tourist.data.models.UserModel;
import com.app.tourist.data.repositories.TokenRepositoryImpl;
import com.app.tourist.data.repositories.UserRepositoryImpl;
import com.app.tourist.data.sources.api.users.ApiUserSourceImpl;
import com.app.tourist.data.sources.local.TokenDataSource;
import com.app.tourist.domain.usecases.user.LoginUser;
import com.app.tourist.ui.view.login.LoggedInUserView;
import com.app.tourist.ui.view.login.LoginResult;
import com.app.tourist.ui.view.login.LoginState;

public class SignupViewModel extends ViewModel {
    private MutableLiveData<SignupState> signupFormState = new MutableLiveData<>();
    private MutableLiveData<SignupResult> signupResult = new MutableLiveData<>();
    private TokenRepositoryImpl tokenRepository;
    private LoginUser loginUseCase;

    public SignupViewModel(){
    }

    public SignupViewModel(UserRepositoryImpl repository){
    }

    public MutableLiveData<SignupState> getSignupFormState() {
        return signupFormState;
    }

    public void setSignupFormState(MutableLiveData<SignupState> signupFormState) {
        this.signupFormState = signupFormState;
    }

    public MutableLiveData<SignupResult> getSignupResult() {
        return signupResult;
    }

    public void setSignupResult(MutableLiveData<SignupResult> signupResult) {
        this.signupResult = signupResult;
    }

    public void signup(String nom, String prenom , String email, String password){
        try{
            Result<UserModel> result = new Result.Error(new Exception("Exception : Veuillez verifier votre data"));
            if (result instanceof Result.Success) {
                UserModel user = (UserModel) ((Result.Success<UserModel>) result).getData();
                LoggedInUserView data = new LoggedInUserView(user.getNom());
                signupResult.setValue(new SignupResult(new LoggedInUserView(data.getDisplayName())));
            } else {
                String message = ((Result.Error) result).getError().getLocalizedMessage().split(":")[1];
                signupResult.setValue(new SignupResult(message));
            }
        }catch (Exception exception){
            String message = exception.getLocalizedMessage().split(":")[1];
            signupResult.setValue(new SignupResult(message));
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
}