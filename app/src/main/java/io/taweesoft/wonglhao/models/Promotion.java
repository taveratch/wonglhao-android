package io.taweesoft.wonglhao.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TAWEESOFT on 4/14/16 AD.
 */
public class Promotion {
    @SerializedName("_id") private String id;
    @SerializedName("name") private String name;
    @SerializedName("bar_id") private String barId;
    @SerializedName("bar_name") private String barName;
    @SerializedName("description") private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarId() {
        return barId;
    }

    public void setBarId(String barId) {
        this.barId = barId;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
