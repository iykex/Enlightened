package xyz.iridiumion.enlightened.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import xyz.iridiumion.enlightened.EnlightenedApplication;
import xyz.iridiumion.enlightened.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        getFragmentManager().beginTransaction().replace(R.id.prefs_content_frame, new EnlightenedSettingsFragment()).commit();
        ActionBar actionBar = getActionBar();
        if (actionBar != null)
            getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class EnlightenedSettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        /*
        Preferences
         */

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            EnlightenedApplication.preferences
                    .registerOnSharedPreferenceChangeListener(
                            this);
        }

        @Override
        public void onResume() {
            super.onResume();

            EnlightenedApplication.preferences
                    .registerOnSharedPreferenceChangeListener(
                            this);
        }

        @Override
        public void onPause() {
            super.onPause();

            EnlightenedApplication.preferences
                    .unregisterOnSharedPreferenceChangeListener(
                            this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            //

        }
    }
}
