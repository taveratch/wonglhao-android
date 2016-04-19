package io.taweesoft.wonglhao.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by TAWEESOFT on 4/15/16 AD.
 */
public class Review implements Serializable{
    @SerializedName("review") private String text;
    @SerializedName("username") private String username;
    @SerializedName("date") private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
