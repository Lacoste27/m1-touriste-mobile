package com.app.tourist.ui.view.profile;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.tourist.R;
import com.app.tourist.core.constant.NetworkPath;
import com.app.tourist.core.constant.UserPath;
import com.app.tourist.databinding.FragmentProfileBinding;
import com.app.tourist.ui.view.home.HomeFragment;
import com.app.tourist.ui.view.login.LoginFragment;
import com.app.tourist.ui.view.signup.SignupFragment;
import com.app.tourist.ui.view.signup.SignupViewModelFactory;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProfileFragement extends Fragment  {
    private static final String CHANNEL_ID ="sqldlqs" ;
    private ProfileViewModel mViewModel;
    private FragmentProfileBinding binding;
    private NavController controller;
    private Button logoutBoutton;

    public static ProfileFragement newInstance() {
        return new ProfileFragement();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.mViewModel =  new ViewModelProvider(this, new ProfileViewModelFactory(getContext())).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.logoutBoutton = view.findViewById(R.id.logoutBoutton);

        this.logoutBoutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.logout();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new HomeFragment())
                        .addToBackStack("back")
                        .commit();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}