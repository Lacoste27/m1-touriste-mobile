package com.app.tourist.data.repositories;

import com.app.tourist.data.sources.local.TokenDataSource;
import com.app.tourist.domain.repositories.TokenRepository;
import com.app.tourist.ui.view.login.LoginFragment;

public class TokenRepositoryImpl implements TokenRepository {

    private final TokenDataSource tokenDataSource;

    private static TokenRepositoryImpl instance;


    public static TokenRepositoryImpl getInstance(TokenDataSource tokenDataSource) {
        if(instance == null){
            instance = new TokenRepositoryImpl(tokenDataSource);
        }
        return instance;
    }

    public TokenRepositoryImpl(TokenDataSource tokenDataSource) {
        this.tokenDataSource = tokenDataSource;
    }

    @Override
    public void saveToken(String token) {
        tokenDataSource.saveToken(token);
    }


    @Override
    public void setIsLogged() {
        this.tokenDataSource.setIsLogged();
    }

    @Override
    public void setIsLogged(String email, String nom, String prenom) {
        this.tokenDataSource.setIsLogged(email, nom, prenom);
    }

    @Override
    public String getUserLogged() {
        String user = this.tokenDataSource.getUserLogged();
        return user;
    }

    @Override
    public String getUserName() {
        String email = this.tokenDataSource.getUserEmail();
        return email;
    }

    @Override
    public boolean getIsLogged() {
        return this.tokenDataSource.getIsLogged();
    }

    @Override
    public String getToken() {
        return tokenDataSource.getToken();
    }

    @Override
    public void clear() {
        tokenDataSource.clear();
    }
}
