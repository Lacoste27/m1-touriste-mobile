package com.app.tourist.data.models;

import com.app.tourist.domain.entities.User;

public class UserModel extends User {

    public UserModel(String id, String nom, String prenom, String email, String password){
        super(id, nom, prenom, email, password);
    }

    public UserModel(User user){
        super(user.getId(), user.getNom(), user.getPrenom(), user.getEmail(), user.getPassword());
    }
}
