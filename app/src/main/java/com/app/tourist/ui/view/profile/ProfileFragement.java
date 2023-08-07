package com.app.tourist.ui.view.profile;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.tourist.R;
import com.app.tourist.databinding.FragmentProfileBinding;
import com.app.tourist.ui.view.home.HomeFragment;

public class ProfileFragement extends Fragment  {
    private static final String CHANNEL_ID ="sqldlqs" ;
    private ProfileViewModel mViewModel;
    private FragmentProfileBinding binding;
    private NavController controller;
    private Button logoutBoutton;

    private TextView welcomeTxt;

    private EditText nomTxt, prenomTxt, emailTxt;

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
        this.welcomeTxt = view.findViewById(R.id.welcomeTxt);
        this.nomTxt = view.findViewById(R.id.editTxtnom);
        this.prenomTxt = view.findViewById(R.id.editTxtPrenom);
        this.emailTxt = view.findViewById(R.id.editTxtEmail);

        this.welcomeTxt.setText(this.welcomeTxt.getText() + this.mViewModel.getUserLogged());

        String nomcomplet = this.mViewModel.getUserLogged();
        String nom = this.mViewModel.getUserLogged().split(" ")[0];
        String prenom = this.mViewModel.getUserLogged().substring(nom.length(), nomcomplet.length());

        this.nomTxt.setText(nom);
        this.prenomTxt.setText(prenom);
        this.emailTxt.setText(this.mViewModel.getUsername());

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