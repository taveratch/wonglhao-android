package io.taweesoft.wonglhao.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by TAWEESOFT on 4/18/16 AD.
 */
public class Recent implements Serializable{
    @SerializedName("_id") private String id;
    @SerializedName("username") private String username;
    @SerializedName("gender") private String gender;
    @SerializedName("user_id") private String userId;
    @SerializedName("bar_id") private String barId;
    @SerializedName("time") private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBarId() {
        return barId;
    }

    public void setBarId(String barId) {
        this.barId = barId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
