package me.dong.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    EditText etUrl;
    Button btnGo, btnBack;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUrl = (EditText) findViewById(R.id.editText_url);
        btnGo = (Button) findViewById(R.id.button_go);
        btnBack = (Button) findViewById(R.id.button_back);
        web = (WebView) findViewById(R.id.webView);

        web.setWebViewClient(new CookWebViewClient());

        //화면 확대/축소 아이콘이 보이도록 설정
        final WebSettings webSet = web.getSettings();
        webSet.setBuiltInZoomControls(true);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strUrl = etUrl.getText().toString();

//                if(strUrl.matches("http://.*"))
                if (!"http://".equals(strUrl.substring(0, 7))) {
                    StringBuilder sbUrl = new StringBuilder();
                    sbUrl.insert(0,"http://");
                    sbUrl.append(strUrl);
                    strUrl = sbUrl.toString();
                }
                etUrl.setText(strUrl);
                web.loadUrl(strUrl);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.goBack();
            }
        });
    }

    class CookWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            EditText editText = (EditText)view.getRootView().findViewById(R.id.editText_url);

            editText.setText(url);
            Log.e(TAG, " " + editText);
        }
    }
}
