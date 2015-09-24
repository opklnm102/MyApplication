package me.dong.calc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";  //Log

    EditText etNum1, etNum2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnMod;
    TextView tvResult;

    BigDecimal bdNum1, bdNum2, bdResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = (EditText) findViewById(R.id.editText_num1);
        etNum2 = (EditText) findViewById(R.id.editText_num2);
        btnAdd = (Button) findViewById(R.id.button_add);
        btnSub = (Button) findViewById(R.id.button_sub);
        btnMul = (Button) findViewById(R.id.button_mul);
        btnDiv = (Button) findViewById(R.id.button_div);
        btnMod = (Button) findViewById(R.id.button_mod);
        tvResult = (TextView) findViewById(R.id.textView_result);

        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnMod.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String strNum1 = etNum1.getText().toString();
        String strNum2 = etNum2.getText().toString();

        if (strNum1.length() == 0 || strNum2.length() == 0) {
            Toast.makeText(MainActivity.this, "계산할 값을 넣어주세요", Toast.LENGTH_LONG).show();
            return;
        } else {
            bdNum1 = new BigDecimal(strNum1);
            bdNum2 = new BigDecimal(strNum2);
        }

        switch (v.getId()) {
            case R.id.button_add:
                bdResult = bdNum1.add(bdNum2);
                break;
            case R.id.button_sub:
                bdResult = bdNum1.subtract(bdNum2);
                break;
            case R.id.button_mul:
                bdResult = bdNum1.multiply(bdNum2);
                break;
            case R.id.button_div:
                if ("0".equals(strNum2)) {
                    showDivsionZero();
                    return;
                }
                bdResult = bdNum1.divide(bdNum2, 2, BigDecimal.ROUND_UP);
                break;
            case R.id.button_mod:
                if ("0".equals(strNum2)) {
                    showDivsionZero();
                    return;
                }
                bdResult = bdNum1.remainder(bdNum2);
                break;
        }
        tvResult.setText(bdResult.toString());
    }

    public void showDivsionZero(){
        Toast.makeText(MainActivity.this, "0으로 나눌수 없습니다.", Toast.LENGTH_LONG).show();
        etNum1.setText("");
        etNum2.setText("");
        tvResult.setText("");
    }
}
