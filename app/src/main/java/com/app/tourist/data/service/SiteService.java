package com.app.tourist.data.service;

import com.app.tourist.data.models.Avis;
import com.app.tourist.data.models.Coordonne;
import com.app.tourist.data.models.SitesModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class SiteService {

    public SitesModel jsonToSitesModel(JSONObject jsonObject){
        SitesModel retour = null;
        try{
            retour = new SitesModel();
            retour.setId(jsonObject.getString("_id"));
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

            //Avis
            JSONArray arrayAvis = new JSONArray(jsonObject.getString("avis"));
            if(arrayAvis.length() != 0){
                Avis[] avis = new Avis[arrayAvis.length()];
                for(int i=0;i < arrayAvis.length(); i++){
                    JSONObject jsonAvis = arrayAvis.getJSONObject(i);
                    avis[i] = new Avis(jsonAvis.getString("note"), jsonAvis.getString("commentaire"), jsonAvis.getString("username"));;
                }
                retour.setAvis(avis);
            }else{
                retour.setAvis(null);
            }

            //retour.setAvis(null);
        }catch (Exception e){
            e.printStackTrace();
            //return null;
        }
        return retour;
    }
}