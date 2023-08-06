package com.app.tourist.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.app.tourist.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private final View mWindow;
    private final View mContents;
    private LayoutInflater mInflater;

    public InfoWindowAdapter(LayoutInflater inflater) {
        mInflater = inflater;
        mWindow =mInflater.inflate(R.layout.custom_info_window, null);
        mContents = mInflater.inflate(R.layout.custom_info_content, null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        render(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        render(marker, mContents);
        return mContents;
    }

    private void render(Marker marker, View view) {
        // Personnalisez le contenu de la fenêtre d'info ici
        TextView titleTextView = view.findViewById(R.id.title);
        TextView snippetTextView = view.findViewById(R.id.snippet);

        titleTextView.setText(marker.getTitle());
        snippetTextView.setText(marker.getSnippet());
    }
}

