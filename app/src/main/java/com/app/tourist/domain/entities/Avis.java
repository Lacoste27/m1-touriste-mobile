package com.app.tourist.domain.entities;

import java.io.Serializable;

public class Avis implements Serializable {
    Integer note;
    String commentaire;

    String username;

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Avis(){

    }

    public Avis(String note, String commentaire, String username){
        this.setNote(Integer.valueOf(note));
        this.setCommentaire(commentaire);
        this.setUsername(username);
    }
}
