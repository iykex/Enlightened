package xyz.iridiumion.enlightened.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

import xyz.iridiumion.enlightened.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        getFragmentManager().beginTransaction().replace(R.id.prefs_content_frame, new EnlightenedSettingsFragment()).commit();
    }

    public static class EnlightenedSettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        /*
        Preferences
         */

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }


        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        }
    }
}
