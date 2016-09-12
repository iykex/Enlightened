package xyz.iridiumion.enlightened;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.jksiezni.permissive.PermissionsGrantedListener;
import com.github.jksiezni.permissive.PermissionsRefusedListener;
import com.github.jksiezni.permissive.Permissive;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;

import xyz.iridiumion.enlightened.editor.HighlightingDefinition;
import xyz.iridiumion.enlightened.editor.IridiumHighlightingEditorJ;
import xyz.iridiumion.enlightened.fragment.EditorFragment;
import xyz.iridiumion.enlightened.highlightingdefinitions.HighlightingDefinitionLoader;
import xyz.iridiumion.enlightened.util.RandomUtils;
import xyz.iridiumion.enlightened.util.FileIOUtil;

public class MainActivity extends AppCompatActivity implements IridiumHighlightingEditorJ.OnTextChangedListener {

    private static final int REQUEST_CODE_BROWSE_FOR_FILE = 1;
    private EditorFragment editorFragment;
    private String currentOpenFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
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
            case R.id.settings:
                showSettings();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveOpenFile() {
        if (currentOpenFilePath == null) {
            new MaterialDialog.Builder(MainActivity.this)
                    .title("No file open")
                    .content("You must open a file in order to save it.")
                    .positiveText("Got it")
                    .show();
            return;
        }
        String textToSave = editorFragment.getEditor().getText().toString();
        try {
            FileIOUtil.writeAllText(currentOpenFilePath, textToSave);
            Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            //e.printStackTrace();
            showExceptionDialog(e);
        }
    }

    private void showSettings() {

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
            String selectedFileExt = RandomUtils.getFileExtension(selectedFilePath);
            //Load file into editor
            try {
                String fileContent = FileIOUtil.readAllText(selectedFilePath);
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
