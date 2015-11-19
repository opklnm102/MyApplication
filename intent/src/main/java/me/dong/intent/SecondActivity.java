package me.dong.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.math.BigDecimal;

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = SecondActivity.class.getSimpleName();

    Button btnReturn;

    BigDecimal dbResult;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Second Activity");

        final Intent intent = getIntent();

        btnReturn = (Button) findViewById(R.id.button_return);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getIntent().getIntExtra("method", 0)) {
                    case 1:
                        dbResult = ((BigDecimal) intent.getSerializableExtra("num1")).add((BigDecimal) intent.getSerializableExtra("num2"));
                        break;
                    case 2:
                        dbResult = ((BigDecimal) intent.getSerializableExtra("num1")).subtract((BigDecimal) intent.getSerializableExtra("num2"));
                        break;
                    case 3:
                        dbResult = ((BigDecimal) intent.getSerializableExtra("num1")).multiply((BigDecimal) intent.getSerializableExtra("num2"));
                        break;
                    case 4:
                        dbResult = ((BigDecimal) intent.getSerializableExtra("num1")).divide((BigDecimal) intent.getSerializableExtra("num2"),BigDecimal.ROUND_UP);
                        break;
                }

                Intent outIntent = new Intent(SecondActivity.this, MainActivity.class);
                outIntent.putExtra("result", dbResult.intValue());
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}
