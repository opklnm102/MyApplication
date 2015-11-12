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
    FloatingActionButton fabWrite;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("간단 일기장");

        dp = (DatePicker) findViewById(R.id.datePicker);
        etDiary = (EditText) findViewById(R.id.editText_diary);
        fabWrite = (FloatingActionButton) findViewById(R.id.fab_write);
        fabWrite.setEnabled(false);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.e(TAG, " onDateChanged");
                fileName = Integer.toString(year) + "-" + Integer.toString(monthOfYear + 1) + "-" + Integer.toString(dayOfMonth) + ".txt";
                String str = readDiary(fileName);
                etDiary.setText(str);
                fabWrite.setEnabled(true);
            }
        });

        fabWrite.setOnClickListener(new View.OnClickListener() {
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

    String readDiary(String fName) {
        String diaryStr = null;
        FileInputStream inFs;

        try {
            inFs = openFileInput(fName);
            byte[] txt = new byte[512];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();

        } catch (IOException e) {
            e.printStackTrace();
            etDiary.setHint("일기 없음");
        }

        return diaryStr;
    }
}
