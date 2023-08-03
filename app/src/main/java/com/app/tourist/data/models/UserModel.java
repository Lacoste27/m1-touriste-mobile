package com.app.tourist.data.models;

import com.app.tourist.domain.entities.User;

public class UserModel extends User {

    public UserModel(int id, String nom, String prenom, String email, String password){
        super(id, nom, prenom, email, password);
    }
}
