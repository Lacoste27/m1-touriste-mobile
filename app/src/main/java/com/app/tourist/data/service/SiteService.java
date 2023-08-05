package com.app.tourist.data.service;

import com.app.tourist.data.models.Coordonne;
import com.app.tourist.data.models.SitesModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class SiteService {

    public SitesModel jsonToSitesModel(JSONObject jsonObject){
        SitesModel retour = null;
        try{
            retour = new SitesModel();
            retour.setNom(jsonObject.getString("nom"));
            retour.setDescription(jsonObject.getString("description"));
            retour.setRegion(jsonObject.getString("region"));
            // Coordonne
            JSONObject jsonCoordonne = new JSONObject(jsonObject.getString("coordonne"));
            retour.setCoordonne(new Coordonne(jsonCoordonne.getString("latitude"), jsonCoordonne.getString("longitude"), jsonCoordonne.getString("localisation")));

            //Photos
            JSONArray arrayPhoto = new JSONArray(jsonObject.getString("photos"));
            if(arrayPhoto.length() != 0){
                String[] photos = new String[arrayPhoto.length()];

                for(int i=0;i < arrayPhoto.length(); i++){
                    photos[i] = arrayPhoto.getString(i);
                }
                retour.setPhotos(photos);
            }else{
                retour.setPhotos(null);
            }

            retour.setAvis(null);
        }catch (Exception e){
            return null;
        }
        return retour;
    }
}
