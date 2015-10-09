package me.dong.calc2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";  //Log

    Integer btnId[] = {R.id.button_num0, R.id.button_num1, R.id.button_num2, R.id.button_num3, R.id.button_num4, R.id.button_num5, R.id.button_num6, R.id.button_num7, R.id.button_num8, R.id.button_num9};

    EditText etNum1, etNum2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    Button btnNums[] = new Button[10];
    TextView tvResult;

    BigDecimal bdNum1, bdNum2, bdResult;
    String strNum1;
    String strNum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid);

        etNum1 = (EditText) findViewById(R.id.editText_num1);
        etNum2 = (EditText) findViewById(R.id.editText_num2);
        btnAdd = (Button) findViewById(R.id.button_add);
        btnSub = (Button) findViewById(R.id.button_sub);
        btnMul = (Button) findViewById(R.id.button_mul);
        btnDiv = (Button) findViewById(R.id.button_div);
        tvResult = (TextView) findViewById(R.id.textView_result);

        for(int i=0; i< btnNums.length; i++){
            btnNums[i] = (Button)findViewById(btnId[i]);
        }

        for(int i=0; i< btnNums.length; i++){
            final int index;
            index = i;

            btnNums[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(etNum1.isFocused() == true){
                        strNum1 = etNum1.getText().toString() + btnNums[index].getText().toString();
                        etNum1.setText(strNum1);
                    }else if(etNum2.isFocused() == true ){
                        strNum2 = etNum2.getText().toString() + btnNums[index].getText().toString();
                        etNum2.setText(strNum2);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"먼저 EditText를 선택하세요",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);

        /* 키보드 안나오게 */
        etNum1.setInputType(0);
        etNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etNum1.getWindowToken(), 0);
            }
        });

        etNum2.setInputType(0);
        etNum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etNum2.getWindowToken(),0);
            }
        });
    }

    @Override
    public void onClick(View v) {

        strNum1 = etNum1.getText().toString();
        strNum2 = etNum2.getText().toString();

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
        }
        tvResult.setText("계산결과: " + bdResult.toString());
    }

    public void showDivsionZero() {
        Toast.makeText(MainActivity.this, "0으로 나눌수 없습니다.", Toast.LENGTH_LONG).show();
        etNum1.setText("");
        etNum2.setText("");
        tvResult.setText("");
    }
}
