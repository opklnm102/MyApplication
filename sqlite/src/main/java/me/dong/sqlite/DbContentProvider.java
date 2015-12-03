package me.dong.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by com on 2015-12-03.
 */
public abstract class DbContentProvider {

    public SQLiteDatabase mDb;

    protected abstract <T> T cursorToEntity(Cursor cursor);

    public DbContentProvider(SQLiteDatabase db) {
        this.mDb = db;
    }

    public long insert(String tableName, ContentValues values) {
        return mDb.insert(tableName, null, values);
    }

    public int update(String tableName, ContentValues values, String selection, String[] selectionArgs){
        return mDb.update(tableName, values, selection, selectionArgs);
    }

    public int delete(String talbeName, String selection, String[] selectionArgs) {
        return mDb.delete(talbeName, selection, selectionArgs);
    }

    public Cursor query(String tableName, String[] columns, String selection, String[] selectionArgs, String sortOrder) {
        final Cursor cursor = mDb.query(tableName, columns, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    public Cursor query(String tableName, String[] columns, String selection, String[] selectionArgs, String sortOrder, String limit){
        return mDb.query(tableName, columns, selection, selectionArgs, null, null, sortOrder,limit);
    }

    public Cursor query(String tableName, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String sortOrder, String limit){
        return mDb.query(tableName, columns, selection, selectionArgs, groupBy, having, sortOrder, limit);
    }

    public Cursor rawQuery(String sql, String[] selectionArgs){
        return mDb.rawQuery(sql, selectionArgs);
    }
}
