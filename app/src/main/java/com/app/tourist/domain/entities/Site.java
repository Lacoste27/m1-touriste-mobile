package com.app.tourist.domain.entities;

import com.app.tourist.data.models.Avis;
import com.app.tourist.data.models.Coordonne;

import java.io.Serializable;

public class Site implements Serializable {
    String nom;
    String description;
    String region;
    Coordonne coordonne;
    String[] photos;
    Avis[] avis;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Coordonne getCoordonne() {
        return coordonne;
    }

    public void setCoordonne(Coordonne coordonne) {
        this.coordonne = coordonne;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public Avis[] getAvis() {
        return avis;
    }

    public void setAvis(Avis[] avis) {
        this.avis = avis;
    }

    public Site(String nom, String description, String region, Coordonne coordonne, String[] photos, Avis[] avis) {
        this.nom = nom;
        this.description = description;
        this.region = region;
        this.coordonne = coordonne;
        this.photos = photos;
        this.avis = avis;
    }

    public Site(String nom, String description, String region, Coordonne coordonne, String[] photos) {
        this.nom = nom;
        this.description = description;
        this.region = region;
        this.coordonne = coordonne;
        this.photos = photos;
    }

    public Site() {
    }
}
