package io.taweesoft.wonglhao.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TAWEESOFT on 4/15/16 AD.
 */
public class Review {
    @SerializedName("review") private String text;
    @SerializedName("username") private String username;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
