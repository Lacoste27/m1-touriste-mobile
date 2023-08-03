package com.app.tourist.ui.view.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.tourist.R;
import com.app.tourist.data.models.ItemsModel;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    private TextView titleTxt, adressTxt, bedTxt, bathTxt, wifiTxt, descriptionTxt;
    private ImageView pic;
    private ItemsModel item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setVariable();
        setView();
    }

    private void setVariable(){
        item = (ItemsModel)getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getTitle());
        adressTxt.setText(item.getAdresse());
        bedTxt.setText(item.getBed() + " bed");
        bathTxt.setText(item.getBath() + " bath");
        descriptionTxt.setText(item.getDescription());

        if(item.isWifi()){
            wifiTxt.setText("Wifi");
        }else{
            wifiTxt.setText("No wifi");
        }

        int drawableResouceId = getResources().getIdentifier(item.getPic(), "drawable", getPackageName());

        Glide.with(this).load(drawableResouceId).into(pic);
    }

    private void setView(){
        titleTxt = findViewById(R.id.titleTxt);
        adressTxt = findViewById(R.id.adressTxt);
        bedTxt = findViewById(R.id.bedTxt);
        bathTxt = findViewById(R.id.bathTxt);
        wifiTxt = findViewById(R.id.wifiTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        pic = findViewById(R.id.pic);
    }
}