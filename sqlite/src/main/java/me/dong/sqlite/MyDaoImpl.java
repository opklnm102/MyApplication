package me.dong.sqlite;

import android.content.ContentValues;
import android.content.Context;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by com on 2015-12-03.
 */
public class MyDaoImpl implements MyDao {

    private MyDbHelper myDbHelper;

    public MyDaoImpl(Context context){
        myDbHelper = MyDbHelper.getInstance(context);
    }

    public void close(){
        myDbHelper.close();
    }

    public void insert(final GroupItem item){
        ContentValues values = new ContentValues();

        values.put(MyDbContracts.GroupEntary.COLUMN_GROUP_NAME, item.getName());
        values.put(MyDbContracts.GroupEntary.COLUMN_GROUP_NUMBER, item.getNumber());

//        long rowId = myDbHelper.insert(MyDbContracts.GroupEntary.TABLE_NAME, values);
//        if(rowId < 0){
//            throw new SQLException("Fail At Insert");
//        }
    }

    @Override
    public GroupItem findGroupByName(String groupName) {
        return null;
    }

    @Override
    public List<GroupItem> findAllGroups() {
        return null;
    }

    @Override
    public boolean addGroup(GroupItem item) {
        return false;
    }

    @Override
    public boolean updateGroupByName() {
        return false;
    }

    @Override
    public boolean deleteAllGroups() {
        return false;
    }
}
