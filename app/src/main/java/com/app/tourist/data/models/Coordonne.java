package com.app.tourist.data.models;

public class Coordonne {
    Float latitude;
    Float longitude;
    String localisation;

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Coordonne(String latitude, String longitude){
        setLatitude(Float.valueOf(latitude));
        setLongitude(Float.valueOf(longitude));
    }

    public Coordonne(String latitude, String longitude, String localisation){
        setLatitude(Float.valueOf(latitude));
        setLongitude(Float.valueOf(longitude));
        setLocalisation(localisation);
    }
}
