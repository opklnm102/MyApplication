package me.dong.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by com on 2015-12-03.
 */
public class MyDbHelper extends SQLiteOpenHelper {

    private static MyDbHelper instance;
    private static SQLiteDatabase db;

    private static final int VER_RELEASE_A = 0x01;
    private static final int CUR_DATABASE_VERSION = VER_RELEASE_A;

    private static final String DATABASE_NAME = "myDb.db";

    public static MyDbHelper getInstance(Context context) {
        //Todo: Double Check 적용
        synchronized (MyDbHelper.class) {
            if (instance == null) {
                instance = new MyDbHelper(context);
                db = instance.getWritableDatabase();
            }
            return instance;
        }
    }

    private MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, CUR_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_GROUP_TABLE = "CREATE TABLE " + MyDbContracts.GroupEntary.TABLE_NAME + " ("
                + MyDbContracts.GroupEntary.COLUMN_GROUP_NAME + " TEXT(20) PRIMARY KEY UNIQUE, "
                + MyDbContracts.GroupEntary.COLUMN_GROUP_NUMBER + " INTEGER" +
                ");";

        db.execSQL(SQL_CREATE_GROUP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        int version = oldVersion;

        if (version != CUR_DATABASE_VERSION) {
            db.execSQL("DROP TABLE IF EXISTS " + MyDbContracts.GroupEntary.TABLE_NAME);

            onCreate(db);

            //Todo: 버전 갱신
            version = CUR_DATABASE_VERSION;
        }
    }

    @Override
    public synchronized void close() {
        if (instance != null)
            instance = null;
        super.close();
    }

    /*
    DB CRUD 메소드 구현
    C - create
    R - read
    U - update
    D - delete
     */
    public long insert(ContentValues values) {
        //execSQL()를 이용해서 직접 SQL문으로 레코드를 추가할 수도 있다.
        return db.insert(MyDbContracts.GroupEntary.TABLE_NAME, null, values);
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return getReadableDatabase().query(MyDbContracts.GroupEntary.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy);
    }

    public int update(ContentValues values, String whereClause, String[] whereArgs){
        //갱신된 레코드수 리턴
        return db.update(MyDbContracts.GroupEntary.TABLE_NAME,
                values,
                whereClause,
                whereArgs);
    }

    public int delete(String whereClause, String[] whereArgs){
        //Todo: 개별, 전체 삭제 구현. 애버노트 메인액티비티 봐야할듯
        //삭제된 레코드수 리턴
        return db.delete(MyDbContracts.GroupEntary.TABLE_NAME, whereClause, whereArgs);
    }
}

//http://overoid.tistory.com/21
//http://wale.oyediran.me/2015/04/02/android-sqlite-dao-design/
//https://www.youtube.com/watch?v=O45YVt_Xgak