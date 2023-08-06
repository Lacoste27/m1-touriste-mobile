package com.app.tourist.data.models;

public class Coordonne extends com.app.tourist.domain.entities.Coordonne {

    public Coordonne(String latitude, String longitude){
        super(latitude,longitude);
    }

    public Coordonne(String latitude, String longitude, String localisation){
        super(latitude,longitude, localisation);
    }
}
