package xyz.iridiumion.enlightened.activity

import android.app.ActionBar
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

import com.afollestad.materialdialogs.MaterialDialog

import xyz.iridiumion.enlightened.EnlightenedApplication
import xyz.iridiumion.enlightened.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        fragmentManager.beginTransaction().replace(R.id.prefs_content_frame, EnlightenedSettingsFragment()).commit()
        val actionBar = actionBar
        if (actionBar != null)
            getActionBar()!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // app icon in action bar clicked; go home
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    class EnlightenedSettingsFragment : PreferenceFragment(), SharedPreferences.OnSharedPreferenceChangeListener {

        /*
        Preferences
         */

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.preferences)
            wirePreferenceEvents()
        }

        private fun wirePreferenceEvents() {
            val aboutPreference = findPreference(resources.getString(R.string.prefs_key_about))
            aboutPreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                //Show About
                MaterialDialog.Builder(activity)
                        .title("About Enlightened")
                        .content(
                                "Enlightened is a free and open source code editor for Android. " + "It is developed by 0xFireball from IridiumIon Software."
                        )
                        .positiveText("Cool")
                        .show()
                true
            }

            val importHighlightingPref = findPreference(resources.getString(R.string.prefs_key_codehighlighting_import))
            importHighlightingPref.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                //Show About
                MaterialDialog.Builder(activity)
                        .title("Coming soon")
                        .content(
                                "This feature is coming soon. It's not ready yet, but it will be soon! " +
                                        "Why don't you join the beta testing program for now?\n" +
                                        "https://play.google.com/apps/testing/xyz.iridiumion.enlightened"
                        )
                        .positiveText("Cool")
                        .show()
                true
            }
        }

        override fun onResume() {
            super.onResume()

            EnlightenedApplication.preferences!!
                    .registerOnSharedPreferenceChangeListener(
                            this)
        }

        override fun onPause() {
            super.onPause()

            EnlightenedApplication.preferences!!
                    .unregisterOnSharedPreferenceChangeListener(
                            this)
        }

        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, s: String) {
            //

        }
    }
}
