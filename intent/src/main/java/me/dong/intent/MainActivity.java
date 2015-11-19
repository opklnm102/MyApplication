package me.dong.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    EditText etNum1, etNum2;
    RadioGroup radioGroup;
    RadioButton rbAdd, rbSub, rbMul, rbDiv;
    Button btnCale;

    int method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        method = 0;

        etNum1 = (EditText) findViewById(R.id.editText_num1);
        etNum2 = (EditText) findViewById(R.id.editText_num2);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rbAdd = (RadioButton) findViewById(R.id.button_add);
        rbSub = (RadioButton) findViewById(R.id.button_sub);
        rbMul = (RadioButton) findViewById(R.id.button_mul);
        rbDiv = (RadioButton) findViewById(R.id.button_div);
        btnCale = (Button) findViewById(R.id.button_calc);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.button_add:
                        method = 1;
                        break;
                    case R.id.button_sub:
                        method = 2;
                        break;
                    case R.id.button_mul:
                        method = 3;
                        break;
                    case R.id.button_div:
                        method = 4;
                        break;
                }
            }
        });

        btnCale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                intent.putExtra("num1", new BigDecimal(etNum1.getText().toString()));
                intent.putExtra("num2", new BigDecimal(etNum2.getText().toString()));
                intent.putExtra("method", method);

                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                int result = data.getIntExtra("result", 0);

                Toast.makeText(MainActivity.this, "결과: " + result, Toast.LENGTH_LONG).show();
            }
        }
    }
}
