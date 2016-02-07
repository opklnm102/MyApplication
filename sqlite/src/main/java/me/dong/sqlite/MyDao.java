package me.dong.sqlite;

import android.content.ContentValues;
import android.content.Context;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by com on 2015-12-03.
 */
public interface MyDao {

    public GroupItem findGroupByName(String groupName);  //이름으로 검색

    public List<GroupItem> findAllGroups();  //전체 검색

    public boolean addGroup(GroupItem item) throws SQLException;  //추가

    //Todo: UI변경시 구현, 현재UI로는 불가
    //public boolean addGroups(List<GroupItem> groupItems);

    public boolean updateGroupByName(String groupName, GroupItem item) throws SQLException;  //이름으로 갱신

    public boolean deleteGroupByName(String groupName) throws SQLException;  //이름으로 삭제

    public boolean deleteAllGroups() throws SQLException;  //전체삭제
}
