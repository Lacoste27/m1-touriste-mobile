package com.app.tourist.ui.view.signup;

import androidx.annotation.StringRes;
import androidx.lifecycle.Observer;
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
import android.widget.Button;
import android.widget.TextView;

import com.app.tourist.R;
import com.app.tourist.ui.view.home.HomeFragment;
import com.app.tourist.ui.view.login.LoggedInUserView;
import com.app.tourist.ui.view.login.LoginResult;
import com.app.tourist.ui.view.login.LoginState;
import com.app.tourist.ui.view.login.LoginViewModel;
import com.app.tourist.ui.view.login.LoginViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class SignupFragment extends Fragment {
    private SignupViewModel viewModel;
    private NavController controller;

    private TextView nomSignupTxt;
    private TextView prenomSignupTxt;
    private TextView emailSignupTxt;
    private TextView passwordSignupTxt;
    private Button signupBoutton;

    public static SignupFragment newInstance() {
        return new SignupFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.viewModel = new ViewModelProvider(this, new SignupViewModelFactory(getContext()))
                .get(SignupViewModel.class);
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.nomSignupTxt = view.findViewById(R.id.nomSignupTxt);
        this.prenomSignupTxt = view.findViewById(R.id.prenomSignupTxt);
        this.emailSignupTxt = view.findViewById(R.id.emailSignupTxt);
        this.passwordSignupTxt = view.findViewById(R.id.passwordSignupTxt);
        this.signupBoutton = view.findViewById(R.id.inscriptionBoutton);

        viewModel.getSignupFormState().observe(this, new Observer<SignupState>() {
            @Override
            public void onChanged(@Nullable SignupState signupState) {
                if (signupState == null) {
                    return;
                }
                signupBoutton.setEnabled(signupState.isDataValid());
                if (signupState.getUsernameError() != null) {
                    emailSignupTxt.setError(getString(signupState.getUsernameError()));
                }
                if (signupState.getPasswordError() != null) {
                    passwordSignupTxt.setError(getString(signupState.getPasswordError()));
                }
            }
        });

        viewModel.getSignupResult().observe(this, new Observer<SignupResult>() {
            @Override
            public void onChanged(@Nullable SignupResult signupResult) {
                if (signupResult == null) {
                    return;
                }

                if (signupResult.getError() != null || signupResult.getErrorMessage() != null) {
                    showLoginFailed(signupResult.getErrorMessage());
                }
                if (signupResult.getSuccess() != null) {
                    updateUiWithUser(signupResult.getSuccess());
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new HomeFragment())
                            .addToBackStack("back")
                            .commit();
                }
            }
        });

        signupBoutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nomSignupTxt.getText().toString();
                String prenom = prenomSignupTxt.getText().toString();
                String email = emailSignupTxt.getText().toString();
                String password = passwordSignupTxt.getText().toString();

                viewModel.signup(nom, prenom, email, password);
            }
        });

    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        Snackbar.make(getView(), welcome, Snackbar.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Snackbar.make(getView(), errorString, Snackbar.LENGTH_LONG).show();
    }

    private void showLoginFailed(String errorString) {
        Snackbar.make(getView(), errorString, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

}