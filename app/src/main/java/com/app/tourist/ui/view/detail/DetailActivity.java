package com.app.tourist.ui.view.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.tourist.MainActivity;
import com.app.tourist.R;
import com.app.tourist.data.models.Avis;
import com.app.tourist.data.models.ItemsModel;
import com.app.tourist.data.models.SitesModel;
import com.app.tourist.ui.adapter.CommentaireAdapter;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DetailActivity extends AppCompatActivity {
    OkHttpClient client;
    private TextView titleTxt, adressTxt, noteTxt, avisTxt, commentireNb, descriptionTxt;
    private ImageView pic;
    private SitesModel item;

    private BaseAdapter adapterCommentaireList;
    private ListView listCommentaire;

    private EditText commentChamp;
    private Button commentButton;

    private String postCommentUrl = "https://back-m1tourist.onrender.com/api/comment/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///API FETCH
        client = new OkHttpClient.Builder().callTimeout(60, TimeUnit.SECONDS).build();
        setContentView(R.layout.activity_detail);

        commentChamp = findViewById(R.id.contenucommentaire);
        commentButton = findViewById(R.id.button);

        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputValue = commentChamp.getText().toString();
                Log.d("CLICK", item.getId());
                //Post the comment
                postComment(inputValue);
            }
        });

        setView();
        setVariable();
    }

    private void postComment(String commentaire){
        try{
            JSONObject postAPI = new JSONObject();
            postAPI.put("idsite", item.getId());
            postAPI.put("username", "Anonymous");
            postAPI.put("note", "4");
            postAPI.put("commentaire", commentaire);

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody requestBody = RequestBody.create(mediaType, postAPI.toString());

            Request request = new Request.Builder().url(postCommentUrl).post(requestBody).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d("API SUCCESS", "SUCCEESS");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            //intent.putExtra("object", items.get(position));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Add this flag
                            getApplicationContext().startActivity(intent);
                        }
                    });
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
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
        ViewCompat.setNestedScrollingEnabled(listCommentaire, true);
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