package com.app.tourist.data.models;

import com.app.tourist.domain.entities.Site;

public class SitesModel extends Site {
    public SitesModel(String nom, String description, String region, Coordonne coordonne, String[] photos, Avis[] avis) {
        super(nom,description,region,coordonne,photos,avis);
    }

    public SitesModel(String nom, String description, String region, Coordonne coordonne, String[] photos) {
        super(nom,description,region,coordonne,photos);
    }

    public SitesModel() {
        super();
    }
}
