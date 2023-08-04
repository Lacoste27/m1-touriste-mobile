package com.app.tourist.ui.view.login;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.app.tourist.ui.view.profile.ProfileViewModel;
import com.app.tourist.ui.view.signup.SignupFragment;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class LoginFragment extends Fragment {

    public LoginViewModel viewModel;

    public View view;

    private TextView emailLoginTxt;
    private TextView passwordLoginTxt;
    private Button loginBoutton;
    private Button signupBoutton;
    private NavController controller;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        this.view = inflater.inflate(R.layout.fragment_login, container, false);


        initView();

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.controller = Navigation.findNavController(view);

        this.passwordLoginTxt = view.findViewById(R.id.passwordLoginTxt);
        this.emailLoginTxt = view.findViewById(R.id.emailLoginTxt);
        this.signupBoutton = view.findViewById(R.id.signupBoutton);
        this.loginBoutton = view.findViewById(R.id.loginBoutton);

        this.signupBoutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.navigation_signup);
            }
        });

        loginBoutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);

                String email = emailLoginTxt.getText().toString() ;
                String password = passwordLoginTxt.getText().toString();

                viewModel.login(email, password);

                viewModel.setToken(preferences, "token");
                controller.navigate(R.id.navigation_home);
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d("Resume tag", "onResume: ");
    }
    private void initView(){
        this.emailLoginTxt = (TextView) this.view.findViewById(R.id.emailLoginTxt);
        this.passwordLoginTxt = (TextView) this.view.findViewById(R.id.passwordLoginTxt);
        this.loginBoutton = (Button) this.view.findViewById(R.id.loginBoutton);
        this.signupBoutton = (Button) this.view.findViewById(R.id.signupBoutton);
    }
}