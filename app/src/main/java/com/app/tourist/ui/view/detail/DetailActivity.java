package com.app.tourist.ui.view.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.tourist.R;
import com.app.tourist.data.models.Avis;
import com.app.tourist.data.models.ItemsModel;
import com.app.tourist.data.models.SitesModel;
import com.app.tourist.ui.adapter.CommentaireAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private TextView titleTxt, adressTxt, noteTxt, avisTxt, commentireNb, descriptionTxt;
    private ImageView pic;
    private SitesModel item;

    private BaseAdapter adapterCommentaireList;
    private ListView listCommentaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setView();
        setVariable();
    }

    private void setVariable(){
        item = (SitesModel) getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getNom());
        adressTxt.setText(item.getRegion());
        noteTxt.setText(item.noteMoyenne());
        avisTxt.setText(item.getNombreAvis()+" avis");
        avisTxt.setPaintFlags(avisTxt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        commentireNb.setText(item.nombreCommentaire()+" commentaires");
        commentireNb.setPaintFlags(commentireNb.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        descriptionTxt.setText(item.getDescription());
        //wifiTxt.setText("Wifi");


        //int drawableResouceId = getResources().getIdentifier(item.getPic(), "drawable", getPackageName());

        if(item.getPhotos() != null){
            Glide.with(this)
                    .load(item.getPhotos()[0])
                    .into(pic);
        }else{
            Glide.with(this)
                    .load("https://lesglobeblogueurs.com/wp-content/uploads/2016/05/pont-ranomafana.jpg")
                    .into(pic);
        }

        //Glide.with(this).load(drawableResouceId).into(pic);
        ArrayList<Avis> avis = new ArrayList<Avis>();
        if(item.getAvis()!=null){
            for(Avis avi: item.getAvis()){
                avis.add(avi);
            }
        }

        listCommentaire = findViewById(R.id.listCommentaires);
        adapterCommentaireList = new CommentaireAdapter(avis, this);
        listCommentaire.setAdapter(adapterCommentaireList);
    }

    private void setView(){
        titleTxt = findViewById(R.id.titleTxt);
        adressTxt = findViewById(R.id.adressTxt);
        noteTxt = findViewById(R.id.note);
        avisTxt = findViewById(R.id.avis);
        commentireNb = findViewById(R.id.nbcommentaire);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        pic = findViewById(R.id.pic);
    }
}