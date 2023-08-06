package com.app.tourist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.app.tourist.R;
import com.app.tourist.data.repositories.TokenRepositoryImpl;
import com.app.tourist.data.sources.local.TokenDataSource;
import com.app.tourist.domain.repositories.TokenRepository;
import com.app.tourist.ui.view.favorite.FavoriteFragment;
import com.app.tourist.ui.view.home.HomeFragment;
import com.app.tourist.ui.view.login.LoginFragment;
import com.app.tourist.ui.view.map.MapFragment;
import com.app.tourist.ui.view.profile.ProfileFragement;
import com.app.tourist.ui.view.setting.SettingsFragment;
import com.app.tourist.ui.view.signup.SignupFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.app.tourist.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private static final long SPLASH_SCREEN_DELAY = 3000; // 2 secondes

    private ActivityMainBinding binding;
    private TokenRepositoryImpl repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);
        this.repository = TokenRepositoryImpl.getInstance(new TokenDataSource(sharedPreferences));

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.navigation_home){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new HomeFragment())
                            .commit();
                    return true;

                } else if(id == R.id.navigation_profile) {
                    if(isLoggedIn()){
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, new ProfileFragement())
                                .commit();
                        return true;
                    } else {
                        Toast.makeText(getApplicationContext(), "Veuillez vous connectez pour voir votre profile", Toast.LENGTH_SHORT).show();

                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, new LoginFragment())
                                .commit();
                        return true;
                    }

                } else if (id == R.id.navigation_map) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new MapFragment())
                            .commit();
                    return true;

                } else if (id == R.id.navigation_favoris) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new FavoriteFragment())
                            .commit();
                    return true;

                } else if (id == R.id.navigation_setting){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new SettingsFragment())
                            .commit();
                    return true;
                }
                return false;
            }
        });

        if (isConnectedToInternet()) {
            Toast.makeText(this, R.string.connecter, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.nonConnecter, Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isLoggedIn() {
        return this.repository.getIsLogged();
    }
    private boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkCapabilities networkCapabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());

            return networkCapabilities != null &&
                    (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
        }

        return false;
    }

}