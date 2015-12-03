package me.dong.sqlite;

import android.content.ContentValues;
import android.content.Context;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by com on 2015-12-03.
 */
public interface MyDao {

    public GroupItem findGroupByName(String groupName);
    public List<GroupItem> findAllGroups();

    public boolean addGroup(GroupItem item);
    //public boolean addGroups(List<GroupItem> groupItems);

    public boolean updateGroupByName();

    public boolean deleteAllGroups();
    //public boolean deleteAllGroups();
}
