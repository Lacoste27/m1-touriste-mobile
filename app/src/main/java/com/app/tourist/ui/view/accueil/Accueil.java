package com.app.tourist.ui.view.accueil;

import androidx.lifecycle.ViewModelProvider;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.app.tourist.R;
import com.app.tourist.ui.view.home.HomeFragment;

public class Accueil extends Fragment {

    private AccueilViewModel mViewModel;

    private Button toListeBoutton;

    public static Accueil newInstance() {
        return new Accueil();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_accueil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.toListeBoutton = view.findViewById(R.id.toListeBoutton);

        VideoView videoView = view.findViewById(R.id.videoView);

        // Uri object to refer the
        // resource from the videoUrl
        Uri uri = Uri.parse("https://res.cloudinary.com/dj1wlcwfy/video/upload/v1691400112/z3fusas9u13cqbqtdten.mp4");

        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(getContext());

        videoView.setMediaController(null);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        videoView.start();

        this.toListeBoutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new HomeFragment())
                        .addToBackStack("back")
                        .commit();
            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(AccueilViewModel.class);
        // TODO: Use the ViewModel
    }

}