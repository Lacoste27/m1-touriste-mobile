package com.app.tourist.data.response;

import java.io.Serializable;

public class UserResponse implements Serializable {
    private String nom;
    private String prenom;
    private String email;
    private String token;

    public UserResponse(String nom, String prenom, String email, String token) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.token = token;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
