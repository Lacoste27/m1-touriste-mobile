package com.app.tourist.domain.repositories;

public interface TokenRepository {
    void saveToken(String token) ;
    void setIsLogged();
    void setIsLogged(String email, String nom, String prenom);
    String getToken();
    String getUserName();
    String getUserLogged();
    boolean getIsLogged();
    void clear();
}
