package me.dong.file;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    DatePicker dp;
    EditText etDiary;
    Button btnWrite;
    String fileName;

    Calendar cal;
    int cYear;
    int cMonth;
    int cDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("간단 일기장");

        dp = (DatePicker) findViewById(R.id.datePicker);
        etDiary = (EditText) findViewById(R.id.editText_diary);
        btnWrite = (Button) findViewById(R.id.button_write);

        cal = Calendar.getInstance();
        cYear = cal.get(Calendar.YEAR);
        cMonth = cal.get(Calendar.MONTH);
        cDay = cal.get(Calendar.DAY_OF_MONTH);

        Log.e(TAG, " onDateChanged" + cYear + cMonth + cDay);

//        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Log.e(TAG, " onDateChanged");
//                fileName = Integer.toString(year) + "-" + Integer.toString(monthOfYear + 1) + "-" + Integer.toString(dayOfMonth) + ".txt";
//                String str = readDiary(fileName);
//                etDiary.setText(str);
//                btnWrite.setEnabled(true);
//            }
//        });

        MyOnDateChangedListener myOnDateChangedListener = new MyOnDateChangedListener();

        dp.init(cYear, cMonth, cDay, myOnDateChangedListener);

                btnWrite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try {
                            FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                            String str = etDiary.getText().toString();
                            outFs.write(str.getBytes());
                            outFs.close();
                            Snackbar.make(view, fileName + "이 저장됨됨", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
        });
    }

    class MyOnDateChangedListener implements DatePicker.OnDateChangedListener {

        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Log.e(TAG, " onDateChanged");
            fileName = Integer.toString(year) + "-" + Integer.toString(monthOfYear + 1) + "-" + Integer.toString(dayOfMonth) + ".txt";
            String str = readDiary(fileName);
            etDiary.setText(str);
            btnWrite.setEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fileName = Integer.toString(cYear) + "-" + Integer.toString(cMonth + 1) + "-" + Integer.toString(cDay) + ".txt";
        String str = readDiary(fileName);
        etDiary.setText(str);
    }

    String readDiary(String fName) {
        String diaryStr = null;
        FileInputStream inFs;

        try {
            inFs = openFileInput(fName);
            byte[] txt = new byte[512];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            btnWrite.setText("수정하기");
            btnWrite.setEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
            etDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }
        Log.e(TAG, " diaryStr " + diaryStr);
        return diaryStr;
    }
}
