package com.app.tourist.ui.view.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextThemeWrapper;

import com.app.tourist.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        // Set the current theme preference summary
        ListPreference themePreference = findPreference("claire");

        // Register preference change listener for themePreference
        themePreference.setOnPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("clair")) {
            ListPreference themePreference = findPreference(key);
            themePreference.setSummary(themePreference.getEntry());

            // Get the selected theme value
            String selectedThemeValue = sharedPreferences.getString(key, "claire");

            switch (selectedThemeValue){
                case "claire":
                    // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
                case "sombre":
                    // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
            }
        }
    }

    @Override
    public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
        if (preference.getKey().equals("claire")) {
            String value = newValue.toString();

            switch (value){
                case "claire":
                    // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
                case "sombre":
                    // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
            }

            // getActivity().recreate();

            return true;
        }

        return false;
    }

    private void applyTheme(int themeResId) {
        if (getActivity() instanceof AppCompatActivity) {
            // Apply the theme to the activity's views programmatically
            getActivity().setTheme(themeResId);

            // Recreate the activity to apply the new theme
            getActivity().recreate();
        }
    }
}