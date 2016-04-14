package io.taweesoft.wonglhao.models;

/**
 * Created by TAWEESOFT on 4/14/16 AD.
 */
public class SideBarItem {
    private int img;
    private String text;

    public int getImg() {
        return img;
    }

    public String getText() {
        return text;
    }

    public SideBarItem(int img, String text) {
        this.img = img;
        this.text = text;
    }
}
