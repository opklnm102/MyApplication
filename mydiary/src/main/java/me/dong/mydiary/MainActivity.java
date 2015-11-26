package me.dong.mydiary;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/*
 o- MainActivity의레이아웃은LinearLayout을사용하고,오른쪽과같이만든다.날짜를표시하기위한
   TextView와일기내용을보여주거나작성할EditText, 저장을실행하는저장Button위젯으로구성한다.

 o- 앱이 실행되면TextView에오늘날짜가표시되고,일기파일이존재하면읽어서EditText에보여준다.

o- 날짜를표시한TextView를터치하면DatePicker 위젯을가지고있는다이얼로그가나타난다.이다이얼로그에서날짜를선택하고
 확인버튼을누르면MainActivity의날짜표시TextView의내용이선택된날짜로변경되고,해당날짜의일기가존재하면읽어온다.

o- 저장버튼을누르면현재TextView에보여지는날짜에해당하는파일에EditText의일기내용을저장한다.

o - 일기는ExternalStorage(sdcard)에저장하고,일기가저장되는폴더를mydiary로한다.앱이실행될때만일mydiary 폴더가
  없다면폴더를만들도록한다.

o- 일기파일이름은년_월_일.txt로한다.

0- 옵션메뉴를만든다.옵션메뉴에는‘다시읽기’, ‘일기삭제’, ‘글씨크기’ 메뉴를포함한다.‘글씨크기’ 메뉴에는
 하위메뉴로‘크게’, ‘보통’, ‘작게’ 메뉴를포함한다.

0-다시불러오기’ 메뉴를선택하면TextView의날짜에해당하는일기파일을다시불러온다.

0-'일기삭제’ 메뉴를선택하면‘20XX년X월X일일기를삭제하시겠습니까?’ 라고묻는다이얼로그를보여주고,
확인을누른경우에TextView의날짜에해당하는일기파일을지우고,EditText의내용도지운다.취소를누르면
아무작업도하지않는다.

0-글씨크기’의‘크게’, ‘보통’, ‘작게’메뉴를선택하면EditText의글씨크기를적절히조절한다.

*/

public class MainActivity extends AppCompatActivity {

    //로깅
    public static final String TAG = MainActivity.class.getSimpleName();

    TextView tvDate;
    Button btnWrite;
    EditText etDiary;
    String fileName;

    //sd카드 경로
    final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
    final File myDir = new File(strSDpath + "/mydir");

    //날짜
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
        setTitle("My Diary");

        //sd카드에 /mydir폴더 생성
        myDir.mkdirs();

        //현재 날짜 셋팅
        cal = Calendar.getInstance();
        cYear = cal.get(Calendar.YEAR);
        cMonth = cal.get(Calendar.MONTH) + 1;
        cDay = cal.get(Calendar.DAY_OF_MONTH);

        //날짜 확인용
        Log.d(TAG, " init cal" + cYear + " " + cMonth + " " + cDay);

        tvDate = (TextView) findViewById(R.id.textView_date);
        btnWrite = (Button) findViewById(R.id.button_save);
        etDiary = (EditText) findViewById(R.id.editText_diary);

        //처음에 읽어오기
        tvDate.setText(cYear + "년 " + cMonth + "월 " + cDay + "일");
        fileName =    makeFileName();
        String str = readDiary(fileName);
        etDiary.setText(str);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        cYear = year;
                        cMonth = monthOfYear + 1;
                        cDay = dayOfMonth;

                        tvDate.setText(cYear + "년 " + cMonth + "월 " + cDay + "일");
                        fileName =    makeFileName();

                        String str = readDiary(fileName);
                        etDiary.setText(str);
                        btnWrite.setEnabled(true);
                    }
                }, cYear, cMonth - 1, cDay).show();
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeDiary(view);
            }
        });
    }

    //파일 이름을 만든다.
    protected String makeFileName(){
        return Integer.toString(cYear) + "_" + Integer.toString(cMonth) + "_" + Integer.toString(cDay) + ".txt";
    }

    //일기 저장, 저장할 디렉토리로 스트림을 열어 저장
    protected void writeDiary(View view) {
        try {
            File file = new File(myDir, fileName);
            FileOutputStream outFs = new FileOutputStream(file);  //스트림 열기

            String str = etDiary.getText().toString();
            outFs.write(str.getBytes());
            outFs.close();  //파일을 쓰고 스트림을 닫는다.

            //저장됨을 사용자에게 알려준다.
            Snackbar.make(view, fileName + "이 저장됨", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            btnWrite.setText("수정하기");
        } catch (IOException e) {
            e.printStackTrace();
            Snackbar.make(view, fileName + "이 저장 실패", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    protected String readDiary(String fName) {

        String diaryStr = null;

        try {
            FileInputStream inFs = new FileInputStream(myDir + "/" + fName);  //저장할 경로를 지정
            byte[] txt = new byte[inFs.available()];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            btnWrite.setText("수정하기");
            btnWrite.setEnabled(true);
        } catch (IOException e) {  //파일이 없어서 읽을 수 없으면 Exception이 뜨므로 예외처리
            e.printStackTrace();
            etDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }
        return diaryStr;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_reRead:
                String  str = readDiary(fileName);
                etDiary.setText(str);
                return true;
            case R.id.action_remove:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("일기 삭제")
                        .setMessage(cYear + "년 " + cMonth + "월 " + cDay + "일 일기를 삭제하시겠습니까?")
                        .setIcon(R.mipmap.ic_launcher)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                try{
                                    File file = new File(myDir, fileName);
                                    file.delete();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                String  str = readDiary(fileName);
                                etDiary.setText(str);
                            }
                        })
                        .setNegativeButton("취소",null)
                        .show();
                return true;
            case R.id.size_big:
                etDiary.setTextSize(30);
                return true;
            case R.id.size_normal:
                etDiary.setTextSize(20);
                return true;
            case R.id.size_small:
                etDiary.setTextSize(10);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
