package io.taweesoft.wonglhao.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by TAWEESOFT on 4/20/16 AD.
 */
public class Nearby implements Serializable{
    @SerializedName("name") private String name;
    @SerializedName("_id") private String id;
    @SerializedName("lat") private double lat;
    @SerializedName("long") private double lon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
