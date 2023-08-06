package com.app.tourist.domain.entities;

import java.io.Serializable;

public class Avis implements Serializable {
    Integer note;
    String commentaire;

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
}
