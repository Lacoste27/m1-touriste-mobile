package com.app.tourist.ui.view.home;

import com.app.tourist.R;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.tourist.data.models.ItemsModel;
import com.app.tourist.data.models.SitesModel;
import com.app.tourist.databinding.FragmentHomeBinding;
import com.app.tourist.domain.entities.Site;
import com.app.tourist.ui.adapter.ItemsAdapter;
import com.app.tourist.ui.adapter.ListItemAdapter;
import com.app.tourist.ui.adapter.SitesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.app.tourist.data.service.SiteService;

public class HomeFragment extends Fragment {

    OkHttpClient client;
    String getUrl = "https://m1-tourist-test.onrender.com/api/sites";
    String searchUrl = "https://m1-tourist-test.onrender.com/api/search?word=";
    private FragmentHomeBinding binding;
    private BaseAdapter adapterList;
    private ListView listViewAll;

    private SiteService siteService;

    private EditText searchText;
    //private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        siteService = new SiteService();
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        View views = inflater.inflate(R.layout.fragment_home, container, false);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

    searchText = root.findViewById(R.id.recherche);


    searchText.addTextChangedListener(new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            rechercher(binding.getRoot(),charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });



        //this.view = views;

        ///API FETCH
        client = new OkHttpClient.Builder().callTimeout(30, TimeUnit.SECONDS).build();

        get(root);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void get(View ro){
        Request request = new Request.Builder().url(getUrl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log.d("API SUCCESSS", response.body().string());


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            String responseData = response.body().string();
                            JSONObject json = new JSONObject(responseData);
                            JSONArray array = new JSONArray(json.get("data").toString());

                            ArrayList<SitesModel> sites = new ArrayList<>();

                            for(int i=0; i < array.length() ; i++){
                                sites.add(siteService.jsonToSitesModel(array.getJSONObject(i)));
                            }

                            listViewAll = ro.findViewById(R.id.listSites);
                            adapterList = new SitesAdapter(sites, ro.getContext());
                            listViewAll.setAdapter(adapterList);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    public void rechercher(View ro, String word){
        Log.d("RECHERCHE URL", searchUrl+word);
        Request request = new Request.Builder().url(searchUrl+word).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log.d("RECHECHER", response.body().string());


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{

                            String responseData = response.body().string();
                            JSONObject json = new JSONObject(responseData);
                            JSONArray array = new JSONArray(json.get("data").toString());

                            ArrayList<SitesModel> sites = new ArrayList<>();

                            for(int i=0; i < array.length() ; i++){
                               sites.add(siteService.jsonToSitesModel(array.getJSONObject(i)));
                            }
                            //sites.add(new SitesModel("BLA BLE","BLA BLA","BLA BLA", null, null, null));

                            listViewAll = ro.findViewById(R.id.listSites);
                            adapterList = new SitesAdapter(sites, ro.getContext());
                            listViewAll.setAdapter(adapterList);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }
}