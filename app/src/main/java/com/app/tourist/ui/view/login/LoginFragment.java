package com.app.tourist.ui.view.login;

import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.tourist.R;
import com.app.tourist.databinding.FragmentFavoriteBinding;
import com.app.tourist.databinding.FragmentLoginBinding;
import com.app.tourist.ui.view.home.HomeFragment;
import com.app.tourist.ui.view.map.MapFragment;
import com.app.tourist.ui.view.profile.ProfileViewModel;
import com.app.tourist.ui.view.signup.SignupFragment;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class LoginFragment extends Fragment {

    public LoginViewModel viewModel;
    public View view;
    public FragmentLoginBinding binding;
    private TextView emailLoginTxt;
    private TextView passwordLoginTxt;
    private Button loginBoutton;
    private Button signupBoutton;
    private ProgressBar loadingProgressBar;
    private static NavHostFragment navhost;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public static NavHostFragment getNavhostInstace(){
        return LoginFragment.navhost;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        this.view = inflater.inflate(R.layout.fragment_login, container, false);
        this.viewModel = new ViewModelProvider(this, new LoginViewModelFactory(getContext()))
                .get(LoginViewModel.class);


        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);

        if(LoginFragment.getNavhostInstace() == null){
            LoginFragment.navhost = navHostFragment;
        }

        this.emailLoginTxt = view.findViewById(R.id.emailTextLogin);
        this.passwordLoginTxt = view.findViewById(R.id.passwordTextLogin);
        this.loginBoutton = view.findViewById(R.id.loginBoutton);
        this.signupBoutton = view.findViewById(R.id.signupBoutton);

        this.signupBoutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new SignupFragment())
                        .addToBackStack("back")
                        .commit();
            }
        });

        viewModel.getLoginFormState().observe(this, new Observer<LoginState>() {
            @Override
            public void onChanged(@Nullable LoginState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginBoutton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    emailLoginTxt.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    emailLoginTxt.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });


        viewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }

                if (loginResult.getError() != null || loginResult.getErrorMessage() != null) {
                    showLoginFailed(loginResult.getErrorMessage());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new HomeFragment())
                            .addToBackStack("back")
                            .commit();
                }
            }
        });

        passwordLoginTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.login(emailLoginTxt.getText().toString(),
                            passwordLoginTxt.getText().toString());
                }
                return false;
            }
        });

        loginBoutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.login(emailLoginTxt.getText().toString(),
                        passwordLoginTxt.getText().toString());
            }
        });

    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d("Resume tag", "onResume: ");
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
}