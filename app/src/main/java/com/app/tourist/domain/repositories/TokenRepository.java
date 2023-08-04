package com.app.tourist.domain.repositories;

public interface TokenRepository {
    void saveToken(String token) ;
    String getToken();
    void clearToken();
}
