package com.app.tourist.domain.repositories;

public interface TokenRepository {
    void saveToken(String token) ;
    void setIsLogged();
    String getToken();
    boolean getIsLogged();
    void clear();
}
