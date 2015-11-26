package me.dong.adapter;

/**
 * Created by com on 2015-11-26.
 */
public class Poster {
    String title;
    Integer imgRes;

    public Poster(String title, Integer imgRes) {
        this.title = title;
        this.imgRes = imgRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImgRes() {
        return imgRes;
    }

    public void setImgRes(Integer imgRes) {
        this.imgRes = imgRes;
    }
}
