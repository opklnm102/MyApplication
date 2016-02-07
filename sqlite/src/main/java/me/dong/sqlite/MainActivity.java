package me.dong.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    Toolbar mToolbar;

    //DB CRUD UI
    EditText etName, etNumber;
    Button btnInit, btnInsert, btnUpdate, btnSelect, btnDelete;

    //DB 결과 UI
    EditText etResultName, etResultNumber;

    //DB작업 클래스
    MyDaoImpl mMyDaoImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle("가수 그룹 관리 DB");

        etName = (EditText) findViewById(R.id.editText_name);
        etNumber = (EditText) findViewById(R.id.editText_number_of_people);
        btnInit = (Button) findViewById(R.id.button_init);
        btnInsert = (Button) findViewById(R.id.button_insert);
        btnUpdate = (Button) findViewById(R.id.button_update);
        btnSelect = (Button) findViewById(R.id.button_select);
        btnDelete = (Button) findViewById(R.id.button_delete);

        etResultName = (EditText) findViewById(R.id.editText_result_name);
        etResultNumber = (EditText) findViewById(R.id.editText_result_number_of_people);

        mMyDaoImpl = new MyDaoImpl(this);

        //db초기화
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sqlDb = myDbHelper.getWritableDatabase();
//                myDbHelper.onUpgrade(sqlDb, 1, 2);
//                sqlDb.close();
            }
        });

        //데이터 insert
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupItem groupItem = new GroupItem(etName.getText().toString(), Integer.parseInt(etNumber.getText().toString()));

                try {
                    if(mMyDaoImpl.addGroup(groupItem)){
                        Toast.makeText(MainActivity.this, "입력됨", Toast.LENGTH_LONG).show();
                        btnSelect.callOnClick();
                    }else{
                        Toast.makeText(MainActivity.this, "입력 실패", Toast.LENGTH_LONG).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

//                sqlDb = myDbHelper.getWritableDatabase();
//                sqlDb.execSQL("INSERT INTO " +
//                        MyDbContracts.GroupEntary.TABLE_NAME + " VALUES ( '"
//                        + etName.getText().toString() + "', "
//                        + etNumber.getText().toString() + ");");
//                sqlDb.close();

//                Toast.makeText(MainActivity.this, "입력됨", Toast.LENGTH_LONG).show();
//                btnSelect.callOnClick();
            }
        });

        //데이터 조회
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strNames = "그룹 이름" + "\r\n" + "-----" + "\r\n";
                String strNumbers = "인원" + "\r\n" + "-----" + "\r\n";

                List<GroupItem> groupItems = mMyDaoImpl.findAllGroups();

                for (GroupItem item : groupItems) {
                    strNames += item.getName() + "\r\n";
                    strNumbers += item.getNumber() + "\r\n";
                }

//                sqlDb = myDbHelper.getReadableDatabase();
//                Cursor cursor = sqlDb.rawQuery("SELECT * FROM " +
//                        MyDbContracts.GroupEntary.TABLE_NAME + ";", null);

//                String strNames = "그룹 이름" + "\r\n" + "-----" + "\r\n";
//                String strNumbers = "인원" + "\r\n" + "-----" + "\r\n";

//                while (cursor.moveToNext()) {
//                    strNames += cursor.getString(0) + "\r\n";
//                    strNumbers += cursor.getString(1) + "\r\n";
//                }

                etResultName.setText(strNames);
                etResultNumber.setText(strNumbers);

//                cursor.close();
//                sqlDb.close();
                Toast.makeText(MainActivity.this
                        , "조회됨", Toast.LENGTH_LONG).show();
            }
        });

        //데이터 갱신
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupItem item = new GroupItem(etName.getText().toString(), Integer.parseInt(etNumber.getText().toString()));

                try {
                    if(mMyDaoImpl.updateGroupByName(etName.getText().toString(), item)){
                        Toast.makeText(MainActivity.this, "수정됨", Toast.LENGTH_LONG).show();

                        btnSelect.callOnClick();
                    }else{
                        Toast.makeText(MainActivity.this, "수정 실패", Toast.LENGTH_LONG).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

//                sqlDb = myDbHelper.getWritableDatabase();

//                sqlDb.execSQL("UPDATE " + MyDbContracts.GroupEntary.TABLE_NAME
//                        + " SET " + MyDbContracts.GroupEntary.COLUMN_GROUP_NUMBER
//                        + " = " + etNumber.getText().toString()
//                        + " WHERE " + MyDbContracts.GroupEntary.COLUMN_GROUP_NAME
//                        + " = '" + etName.getText().toString() + "';");
//
//                sqlDb.close();

//                Toast.makeText(MainActivity.this, "수정됨", Toast.LENGTH_LONG).show();
//
//                btnSelect.callOnClick();
            }
        });

        //데이터 삭제
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if(mMyDaoImpl.deleteGroupByName(etName.getText().toString())){
                        Toast.makeText(MainActivity.this, "삭제됨", Toast.LENGTH_LONG).show();

                        btnSelect.callOnClick();
                    }else{
                        Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_LONG).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

//                sqlDb = myDbHelper.getWritableDatabase();
//
//                Log.e(TAG, "DELETE FROM " + MyDbContracts.GroupEntary.TABLE_NAME
//                        + " WHERE " + MyDbContracts.GroupEntary.COLUMN_GROUP_NAME
//                        + " = '" + etName.getText().toString() + "';");
//
//                sqlDb.execSQL("DELETE FROM " + MyDbContracts.GroupEntary.TABLE_NAME
//                        + " WHERE " + MyDbContracts.GroupEntary.COLUMN_GROUP_NAME
//                        + " = '" + etName.getText().toString() + "';");
//
//                sqlDb.close();

//                Toast.makeText(MainActivity.this, "삭제됨", Toast.LENGTH_LONG).show();
//
//                btnSelect.callOnClick();
            }
        });
    }
}
