package me.dong.sqlite;

import android.provider.BaseColumns;

/**
 * Created by com on 2015-12-03.
 */
public class MyDbContracts {

    public static final class GroupEntary implements BaseColumns{

        public static final String TABLE_NAME = "groupTbl";

        public static final String COLUMN_GROUP_NAME = "gName";
        public static final String COLUMN_GROUP_NUMBER = "gNumber";

        public static final String[] PROJECTION_ALL = { _ID, COLUMN_GROUP_NAME, COLUMN_GROUP_NUMBER};
    }
}
