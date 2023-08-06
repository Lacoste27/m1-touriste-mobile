package com.app.tourist.ui.view.signup;

import android.content.Context;
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
import com.app.tourist.domain.usecases.user.SignupUser;
import com.app.tourist.ui.view.login.LoggedInUserView;
import com.app.tourist.ui.view.login.LoginResult;
import com.app.tourist.ui.view.login.LoginState;

public class SignupViewModel extends ViewModel {
    private MutableLiveData<SignupState> signupFormState = new MutableLiveData<>();
    private MutableLiveData<SignupResult> signupResult = new MutableLiveData<>();
    private TokenRepositoryImpl tokenRepository;
    private SignupUser signupUseCase;

    private Context context;

    public SignupViewModel(){
    }

    public SignupViewModel(UserRepositoryImpl repository, Context context){
        this.signupUseCase = new SignupUser(repository);
        this.context = context;
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
            Result<UserModel> result = this.signupUseCase.signup(nom,prenom, email, password);
            if (result instanceof Result.Success) {
                UserModel user = (UserModel) ((Result.Success<UserModel>) result).getData();
                LoggedInUserView data = new LoggedInUserView(user.getNom());
                setIsLogged();
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

    public void setToken(String token){
        TokenDataSource tokenDataSource = new TokenDataSource(this.context);
        this.tokenRepository = TokenRepositoryImpl.getInstance(tokenDataSource);
        this.tokenRepository.saveToken(token);
    }

    public void setIsLogged(){
        TokenDataSource tokenDataSource = new TokenDataSource(this.context);
        this.tokenRepository = TokenRepositoryImpl.getInstance(tokenDataSource);
        this.tokenRepository.setIsLogged();
    }

    public String getToken(){
        TokenDataSource tokenDataSource = new TokenDataSource(this.context);
        this.tokenRepository = TokenRepositoryImpl.getInstance(tokenDataSource);
        return this.tokenRepository.getToken();
    }
}