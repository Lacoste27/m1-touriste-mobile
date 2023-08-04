package com.app.tourist.data.repositories;

import com.app.tourist.data.sources.local.TokenDataSource;
import com.app.tourist.domain.repositories.TokenRepository;

public class TokenRepositoryImpl implements TokenRepository {

    private final TokenDataSource tokenDataSource;

    public TokenRepositoryImpl(TokenDataSource tokenDataSource) {
        this.tokenDataSource = tokenDataSource;
    }

    @Override
    public void saveToken(String token) {
        tokenDataSource.saveToken(token);
    }

    @Override
    public String getToken() {
        return tokenDataSource.getToken();
    }

    @Override
    public void clearToken() {
        tokenDataSource.clearToken();
    }
}
