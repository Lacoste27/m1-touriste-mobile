package com.app.tourist.ui.view.login;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.tourist.R;
import com.app.tourist.ui.view.profile.ProfileViewModel;

import org.w3c.dom.Text;

public class LoginFragment extends Fragment {

    private LoginViewModel viewModel;

    private TextView emailLoginTxt;
    private TextView passwordLoginTxt;
    private Button loginBoutton;
    private Button signupBoutton;

    private View view;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        this.view = inflater.inflate(R.layout.fragment_login, container, false);

        initView();

        this.signupBoutton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Boutton click", "onClick: signup boutton");
                Toast.makeText(requireContext(),"S'inscrire boutton", Toast.LENGTH_LONG).show();
            }
        });

        this.viewModel.login("robsona@email.com", "passs");

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    private void initView(){
        this.emailLoginTxt = (TextView) this.view.findViewById(R.id.emailLoginTxt);
        this.passwordLoginTxt = (TextView) this.view.findViewById(R.id.passwordLoginTxt);
        this.loginBoutton = (Button) this.view.findViewById(R.id.loginBoutton);
        this.signupBoutton = (Button) this.view.findViewById(R.id.signupBoutton);

    }

}