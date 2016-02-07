package me.dong.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by com on 2015-12-03.
 */
public class MyDaoImpl implements MyDao {

    private MyDbHelper myDbHelper;

    public MyDaoImpl(Context context) {
        myDbHelper = MyDbHelper.getInstance(context);
    }

    public void init() {
//        sqlDb = myDbHelper.getWritableDatabase();
//        myDbHelper.onUpgrade(sqlDb, 1, 2);
//        sqlDb.close();
    }

    public void close() {
        myDbHelper.close();
    }

    @Override
    public GroupItem findGroupByName(String groupName) {

        Cursor cursor = myDbHelper.query(MyDbContracts.GroupEntary.PROJECTION_ALL,
                MyDbContracts.GroupEntary.COLUMN_GROUP_NAME + "=?", new String[]{groupName}, null, null, null);

        cursor.moveToFirst();
        GroupItem item = new GroupItem(cursor.getString(0), Integer.parseInt(cursor.getString(1)));

        cursor.close();

        return item;
    }

    @Override
    public List<GroupItem> findAllGroups() {

        List<GroupItem> groupItemList = new ArrayList<>();
        Cursor cursor = myDbHelper.query(MyDbContracts.GroupEntary.PROJECTION_ALL, null, null, null, null, null);

        while (cursor.moveToNext()) {
            GroupItem item = new GroupItem(cursor.getString(0), Integer.parseInt(cursor.getString(1)));
            groupItemList.add(item);
        }

        cursor.close();

        return groupItemList;
    }

    @Override
    public boolean addGroup(GroupItem item) throws SQLException {
        ContentValues values = new ContentValues();

        values.put(MyDbContracts.GroupEntary.COLUMN_GROUP_NAME, item.getName());
        values.put(MyDbContracts.GroupEntary.COLUMN_GROUP_NUMBER, item.getNumber());

        long rowId = myDbHelper.insert(values);
        if (rowId < 0) {
//            throw new SQLException("Fail At Insert");
            return false;
        }
        return true;
    }

    @Override
    public boolean updateGroupByName(String groupName, GroupItem item) throws SQLException {
        ContentValues values = new ContentValues();

        values.put(MyDbContracts.GroupEntary.COLUMN_GROUP_NAME, item.getName());
        values.put(MyDbContracts.GroupEntary.COLUMN_GROUP_NUMBER, item.getNumber());

        String[] whereArgs = {groupName};

        int rowId = myDbHelper.update(values, MyDbContracts.GroupEntary.COLUMN_GROUP_NAME + "=?", whereArgs);
        if (rowId < 0) {  //실패
//            throw new SQLException("Fail At update");
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteGroupByName(String groupName) throws SQLException {
        String[] whereArgs = {groupName};
        int rowId = myDbHelper.delete(MyDbContracts.GroupEntary.COLUMN_GROUP_NAME + "=?", whereArgs);
        if (rowId < 0) {  //실패
//            throw new SQLException("Fail At delete");
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAllGroups() throws SQLException {

        int rowId = myDbHelper.delete(null, null);
        if (rowId < 0) {  //실패
//            throw new SQLException("Fail At delete All");
            return false;
        }
        return true;
    }
}
