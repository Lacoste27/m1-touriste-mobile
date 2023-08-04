package com.app.tourist.ui.view.signup;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.tourist.R;
import com.app.tourist.ui.view.login.LoginViewModel;

public class SignupFragment extends Fragment {

    private SignupViewModel viewModel;
    private NavController controller;

    public static SignupFragment newInstance() {
        return new SignupFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.viewModel = new ViewModelProvider(this).get(SignupViewModel.class);
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.controller = Navigation.findNavController(view);
    }

    @Override
    public void onResume(){
        super.onResume();

        String token  =viewModel.getToken(getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE));
        if(token.equals("")){
            controller.navigate(R.id.navigation_login);
        }
    }

}