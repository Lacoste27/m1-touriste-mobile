package com.app.tourist.ui.view.map;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.tourist.R;
import com.app.tourist.data.models.SitesModel;
import com.app.tourist.data.service.SiteService;
import com.app.tourist.ui.adapter.SitesAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    OkHttpClient client;
    String getUrl = "https://m1-tourist-test.onrender.com/api/sites";
    private MapViewModel mViewModel;
    private GoogleMap map;
    private MapView mapView;
    private SiteService siteService;

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        siteService = new SiteService();
        client = new OkHttpClient.Builder().callTimeout(30, TimeUnit.SECONDS).build();
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        Request request = new Request.Builder().url(getUrl).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("API FAILURE",   e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("API SUCCESSS", response.body().string());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            String responseData = response.body().string();
                            JSONObject json = new JSONObject(responseData);
                            JSONArray array = new JSONArray(json.get("data").toString());

                            ArrayList<SitesModel> sites = new ArrayList<>();

                            for(int i=0; i < array.length() ; i++){
                                SitesModel site = siteService.jsonToSitesModel(array.getJSONObject(i));
                                sites.add(siteService.jsonToSitesModel(array.getJSONObject(i)));

                                LatLng sydney = new LatLng(site.getCoordonne().getLatitude(), site.getCoordonne().getLongitude());
                                map.addMarker(new MarkerOptions().position(sydney).title(site.getNom()));
                            }

                            LatLng camera = new LatLng(sites.get(0).getCoordonne().getLatitude(), sites.get(0).getCoordonne().getLatitude());

                            map.moveCamera(CameraUpdateFactory.newLatLng(camera));


                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MapViewModel.class);
        // TODO: Use the ViewModel
    }

    public void fillMap(View root, GoogleMap map){

    }
}