package me.dong.sqlite;

/**
 * Created by com on 2015-12-03.
 */
public class GroupItem {

    private int id;
    private String name;
    private int number;

    public GroupItem() {
    }

    public GroupItem(int id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
