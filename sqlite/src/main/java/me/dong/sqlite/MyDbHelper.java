package me.dong.sqlite;

import android.content.ContentValues;
import android.content.Context;
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

    public static MyDbHelper getInstance(Context context){
        synchronized (MyDbHelper.class){
            if(instance == null){
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
                + MyDbContracts.GroupEntary.COLUMN_GROUP_NAME + " TEXT(20) PRIMARY KEY, "
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

            version = CUR_DATABASE_VERSION;
        }
    }

    @Override
    public synchronized void close() {
        if(instance != null)
            instance = null;
        super.close();
    }

//    public long insert(String table, ContentValues values){
//        return
//    }
}

//http://overoid.tistory.com/21
//http://wale.oyediran.me/2015/04/02/android-sqlite-dao-design/
