package xyz.iridiumion.enlightened.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;

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
            wirePreferenceEvents();
        }

        private void wirePreferenceEvents() {
            Preference aboutPreference = findPreference(getResources().getString(R.string.prefs_key_about));
            aboutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    //Show About
                    new MaterialDialog.Builder(getActivity())
                            .title("About Enlightened")
                            .content(
                                    "Enlightened is a free and open source code editor for Android. " +
                                            "It is developed by 0xFireball from IridiumIon Software."
                            )
                            .positiveText("Cool")
                            .show();
                    return true;
                }
            });

            Preference importHighlightingPref = findPreference(getResources().getString(R.string.prefs_key_codehighlighting_import));
            importHighlightingPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    //Show About
                    new MaterialDialog.Builder(getActivity())
                            .title("Coming soon")
                            .content(
                                    "This feature is coming soon. It's not ready yet, but it will be soon! " +
                                            "Why don't you join the beta testing program for now?\n" +
                                            "https://play.google.com/apps/testing/xyz.iridiumion.enlightened"
                            )
                            .positiveText("Cool")
                            .show();
                    return true;
                }
            });
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
