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
import android.widget.Toast;

import com.app.tourist.R;
import com.app.tourist.data.models.SitesModel;
import com.app.tourist.data.service.SiteService;
import com.app.tourist.ui.adapter.InfoWindowAdapter;
import com.app.tourist.ui.adapter.SitesAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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
import okhttp3.ResponseBody;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener{
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

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        return view;
    }




    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Request request = new Request.Builder().url(getUrl).build();

        LatLng locationToFocusOn =new LatLng(-18.909920, 47.508597);
        float desiredRotation = 16.0f; // Change this value to your desired rotation angle in degrees

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(locationToFocusOn)
                .zoom(5.8f) // Use the current zoom level
                .bearing(desiredRotation) // Set the desired rotation angle
                .tilt(googleMap.getCameraPosition().tilt) // Use the current tilt angle
                .build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.setOnInfoWindowClickListener(MapFragment.this);

        //googleMap.setInfoWindowAdapter(new InfoWindowAdapter(getLayoutInflater()));
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("API FAILURE",   e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.peekBody(Long.MAX_VALUE)) {

                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected response code: " + response);
                    }

                    String body = responseBody.string();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                JSONObject json = new JSONObject(body);
                                JSONArray array = new JSONArray(json.get("data").toString());

                                ArrayList<SitesModel> sites = new ArrayList<>();

                                for(int i=0; i < array.length() ; i++){
                                    SitesModel site = siteService.jsonToSitesModel(array.getJSONObject(i));
                                    sites.add(siteService.jsonToSitesModel(array.getJSONObject(i)));

                                    LatLng sydney = new LatLng(site.getCoordonne().getLatitude(), site.getCoordonne().getLongitude());

                                    googleMap.addMarker(new MarkerOptions().position(sydney).title(site.getNom()));
                                }




                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }

        });


    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {
        Log.d("MARKER CLICKED", "onInfoWindowClick: ");
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