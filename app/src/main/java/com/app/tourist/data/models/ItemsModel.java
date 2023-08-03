package com.app.tourist.data.models;

import com.app.tourist.domain.entities.Items;

public class ItemsModel extends Items {
    public ItemsModel(String title, String adresse, String description, int bed, int bath, int price, String pic, boolean wifi) {
        super(title, adresse, description, bed, bath, price, pic, wifi);
    }
}
