package xyz.iridiumion.enlightened.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.jksiezni.permissive.PermissionsGrantedListener;
import com.github.jksiezni.permissive.PermissionsRefusedListener;
import com.github.jksiezni.permissive.Permissive;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;

import xyz.iridiumion.enlightened.EnlightenedApplication;
import xyz.iridiumion.enlightened.R;
import xyz.iridiumion.enlightened.editor.HighlightingDefinition;
import xyz.iridiumion.enlightened.editor.IridiumHighlightingEditorJ;
import xyz.iridiumion.enlightened.fragment.EditorFragment;
import xyz.iridiumion.enlightened.highlightingdefinitions.HighlightingDefinitionLoader;
import xyz.iridiumion.enlightened.util.FileIOUtil;
import xyz.iridiumion.enlightened.util.RandomUtils;
import xyz.iridiumion.enlightened.widget.TouchThruDrawerLayout;

public class MainActivity extends AppCompatActivity implements IridiumHighlightingEditorJ.OnTextChangedListener {

    private static final int REQUEST_CODE_BROWSE_FOR_FILE = 1;
    private EditorFragment editorFragment;
    private String currentOpenFilePath;
    private TouchThruDrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private View menuFrame;
    private Toolbar toolbar;
    private ListView recentFilesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSystemBars(this);
        initToolbar();
        initDrawer();
        initRecentList();

        if (savedInstanceState == null ||
                (editorFragment = (EditorFragment)
                        getSupportFragmentManager().findFragmentByTag(
                                EditorFragment.TAG)) == null) {
            editorFragment = new EditorFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.content_frame,
                            editorFragment,
                            EditorFragment.TAG)
                    .commit();
        }

    }

    private static void initSystemBars(AppCompatActivity activity) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle state) {
        super.onPostCreate(state);

        drawerToggle.syncState();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String insert_tab_visible_key = getResources().getString(R.string.prefs_key_show_tab_tool);

        menu.findItem(R.id.insert_tab)
                .setVisible(EnlightenedApplication.Companion.getPreferences()
                        .getBoolean(insert_tab_visible_key, true));

        /*
        menu.findItem(R.id.save_file)
                .setEnabled(currentOpenFilePath != null);
        */

        return super.onPrepareOptionsMenu(menu);
    }

    private void initDrawer() {
        drawerLayout = (TouchThruDrawerLayout) findViewById(
                R.id.drawer_layout);

        menuFrame = findViewById(R.id.menu_frame);

        drawerToggle =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.drawer_open,
                        R.string.drawer_close) {
                    public void onDrawerClosed(View view) {
                        supportInvalidateOptionsMenu();
                    }

                    public void onDrawerOpened(View view) {
                        supportInvalidateOptionsMenu();
                    }
                };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
    }


    private void initRecentList() {
        recentFilesListView = (ListView) findViewById(R.id.recent_files);
        recentFilesListView.setEmptyView(findViewById(R.id.no_files));
        recentFilesListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {
                        selectRecentFile(id);
                        closeDrawer();
                    }
                });
    }

    private void selectRecentFile(long id) {

    }

    private void closeDrawer() {
        if (drawerLayout == null)
            return;

        drawerLayout.closeDrawer(menuFrame);
    }

    private void openDrawer() {
        if (drawerLayout == null)
            return;

        drawerLayout.openDrawer(menuFrame);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.en_toolbar);
        setSupportActionBar(toolbar);
    }

    private void updateUiToPreferences() {
        invalidateOptionsMenu();

        String editor_font_size_key = getResources().getString(R.string.prefs_key_font_size);
        float newFontSize = Float.parseFloat(EnlightenedApplication.Companion.getPreferences().getString(editor_font_size_key, "12.0"));
        editorFragment.getEditor().setTextSize(TypedValue.COMPLEX_UNIT_SP, newFontSize);
        String editor_tab_size_key = getResources().getString(R.string.prefs_key_tab_size);
        int newTabSize = (int) Float.parseFloat(EnlightenedApplication.Companion.getPreferences().getString(editor_tab_size_key, "4.0"));
        editorFragment.getEditor().setTabWidth(newTabSize);
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateUiToPreferences();
        updateRecentFilesList();
    }

    @Override
    public void onTextChanged(String text) {
        /*
        if (!ShaderEditorApplication
                .preferences
                .doesRunOnChange())
            return;
        */
        if (editorFragment.hasErrorLine()) {
            editorFragment.clearError();
            editorFragment.updateHighlighting();
        }
        String autosave_key = getResources().getString(R.string.prefs_key_autosave);
        if (EnlightenedApplication.Companion.getPreferences()
                .getBoolean(autosave_key, true) && currentOpenFilePath != null) {
            saveOpenFile(false, false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insert_tab:
                insertTab();
                return true;
            case R.id.open_file:
                openFile();
                return true;
            case R.id.save_file:
                saveOpenFile();
                return true;
            case R.id.save_file_as:
                showSaveFileAsDialog();
                return true;
            case R.id.settings:
                showSettings();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateRecentFilesList() {
        //TODO: actually implement
        showNoRecentFilesAvailable();
    }

    private void showNoRecentFilesAvailable() {
        View progressView;
        View textView;

        if ((progressView = findViewById(
                R.id.progress_bar)) == null ||
                (textView = findViewById(
                        R.id.no_recent_files_message)) == null)
            return;

        progressView.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
    }

    private void saveOpenFile() {
        saveOpenFile(true, true);
    }

    private void saveOpenFile(boolean showErrorIfAccident, final boolean showToast) {
        if (currentOpenFilePath == null && showErrorIfAccident) {
            /*
            new MaterialDialog.Builder(MainActivity.this)
                    .title("No file open")
                    .content("You must open a file in order to save it.")
                    .positiveText("Got it")
                    .show();
            */
            showSaveFileAsDialog();
            return;
        }
        new Permissive.Request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .whenPermissionsGranted(new PermissionsGrantedListener() {
                    @Override
                    public void onPermissionsGranted(String[] permissions) throws SecurityException {
                        // given permissions are granted

                        String textToSave = editorFragment.getEditor().getText().toString();
                        try {
                            FileIOUtil.INSTANCE.writeAllText(currentOpenFilePath, textToSave);
                            if (showToast)
                                Toast.makeText(MainActivity.this, "File saved", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            //e.printStackTrace();
                            showExceptionDialog(e);
                        }
                    }
                })
                .whenPermissionsRefused(new PermissionsRefusedListener() {
                    @Override
                    public void onPermissionsRefused(String[] permissions) {
                        // given permissions are refused
                        new MaterialDialog.Builder(MainActivity.this)
                                .title("Permission not granted")
                                .content("Enlightened needs your permission to load and save files. Please grant this permission in settings.")
                                .positiveText("Got it")
                                .show();
                    }
                })
                .execute(MainActivity.this);
    }

    private void showSaveFileAsDialog() {
        MaterialDialog saveFileAsDialog = new MaterialDialog.Builder(this)
                .title("Save File As")
                .customView(R.layout.dialog_save_file_as, true)
                .positiveText("Save As")
                .negativeText("Cancel")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        View view = dialog.getCustomView();
                        if (view == null)
                            return;
                        String saveDirectoryInput = ((EditText) view.findViewById(R.id.sfad_dir_path)).getText().toString();
                        String saveFileName = ((EditText) view.findViewById(R.id.sfad_file_name)).getText().toString();
                        currentOpenFilePath = new File(saveDirectoryInput, saveFileName).getAbsolutePath();
                        saveOpenFile();
                    }
                })
                .show();
        View view = saveFileAsDialog.getCustomView();
        EditText saveDirectoryInput = null;
        if (view != null) {
            saveDirectoryInput = (EditText) view.findViewById(R.id.sfad_dir_path);
            saveDirectoryInput.setText(Environment.getExternalStorageDirectory().getPath());
        }
    }

    private void showSettings() {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void openFile() {
        new Permissive.Request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .whenPermissionsGranted(new PermissionsGrantedListener() {
                    @Override
                    public void onPermissionsGranted(String[] permissions) throws SecurityException {
                        // given permissions are granted
                        browseForFile();
                    }
                })
                .whenPermissionsRefused(new PermissionsRefusedListener() {
                    @Override
                    public void onPermissionsRefused(String[] permissions) {
                        // given permissions are refused
                        new MaterialDialog.Builder(MainActivity.this)
                                .title("Permission not granted")
                                .content("Enlightened needs your permission to load and save files. Please grant this permission in settings.")
                                .positiveText("Got it")
                                .show();
                    }
                })
                .execute(MainActivity.this);
    }

    private void browseForFile() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(REQUEST_CODE_BROWSE_FOR_FILE)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_BROWSE_FOR_FILE && resultCode == RESULT_OK) {
            String selectedFilePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            File selectedFile = new File(selectedFilePath);
            String selectedFileExt = RandomUtils.INSTANCE.getFileExtension(selectedFilePath);
            //Load file into editor
            try {
                String fileContent = FileIOUtil.INSTANCE.readAllText(selectedFilePath);
                editorFragment.getEditor().setText(fileContent);
                HighlightingDefinitionLoader definitionLoader = new HighlightingDefinitionLoader();
                HighlightingDefinition highlightingDefinition = definitionLoader.selectDefinitionFromFileExtension(selectedFileExt);
                editorFragment.getEditor().loadHighlightingDefinition(highlightingDefinition);
                currentOpenFilePath = selectedFilePath;
                Toast.makeText(this, "File loaded", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                //e.printStackTrace();
                showExceptionDialog(e);
            }
        }
    }

    private void showExceptionDialog(Exception e) {
        new MaterialDialog.Builder(MainActivity.this)
                .title("Oops!")
                .content(String.format("An unexpected error occurred: %s", e.getMessage() == null ?
                        e.toString() : e.getMessage()))
                .positiveText("Got it")
                .show();
    }

    private void insertTab() {
        editorFragment.insertTab();
    }
}
