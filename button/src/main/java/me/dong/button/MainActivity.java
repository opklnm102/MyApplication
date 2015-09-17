package me.dong.button;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNate;
    private Button btnEmergency;
    private Button btnGallery;
    private Button btnFinish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNate = (Button) findViewById(R.id.button_nate);
        btnEmergency = (Button) findViewById(R.id.button_emergency);
        btnGallery = (Button) findViewById(R.id.button_gallery);
        btnFinish = (Button) findViewById(R.id.button_finish);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button_nate:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nate.com"));
                startActivity(intent);
                break;
            case R.id.button_emergency:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/911"));
                startActivity(intent);
                break;
            case R.id.button_gallery:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                startActivity(intent);
                break;
            case R.id.button_finish:
                finish();
                break;

        }
    }
}
