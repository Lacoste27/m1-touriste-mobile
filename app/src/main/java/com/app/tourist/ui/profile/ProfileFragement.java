package com.app.tourist.ui.profile;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.tourist.R;
import com.app.tourist.core.constant.NetworkPath;
import com.app.tourist.core.constant.UserPath;
import com.app.tourist.core.utils.ApiResponse;
import com.app.tourist.core.utils.Result;
import com.app.tourist.data.service.ApiService;
import com.app.tourist.data.service.UserService;
import com.app.tourist.databinding.FragmentNotificationsBinding;
import com.app.tourist.databinding.FragmentProfileBinding;
import com.app.tourist.ui.notifications.NotificationsViewModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProfileFragement extends Fragment {

    private static final String CHANNEL_ID ="sqldlqs" ;
    private ProfileViewModel mViewModel;
    private FragmentProfileBinding binding;

    public static ProfileFragement newInstance() {
        return new ProfileFragement();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textProfile;
        profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        try {
            String url = NetworkPath.host+ UserPath.login;

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("email", "robsona@mail.com")
                    .add("password", "passs")
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    // Handle failure here
                    Log.e("OkHttp Error", e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    // Handle the response here
                    String responseBody = response.body().toString();
                    Log.d("c Response", responseBody);
                }
            });

        }catch (Exception exception){
            Log.d("apiexception", "onCreateViewException:"+exception.getMessage());
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}