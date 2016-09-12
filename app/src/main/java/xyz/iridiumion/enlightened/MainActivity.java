package xyz.iridiumion.enlightened;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import xyz.iridiumion.enlightened.fragment.EditorFragment;

public class MainActivity extends AppCompatActivity {

    private EditorFragment editorFragment;

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
}
