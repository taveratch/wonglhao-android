package io.taweesoft.wonglhao.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TAWEESOFT on 4/15/16 AD.
 */
public class Bar implements Serializable{
    @SerializedName("_id") private String id;
    @SerializedName("name") private String name;
    @SerializedName("lat") private double lat;
    @SerializedName("long") private double lon;
    @SerializedName("star") private double star;
    @SerializedName("rate") private int rate;
    @SerializedName("description") private String description;
    @SerializedName("banner_image_url") private String bannerUrl;
    @SerializedName("image_url") private String imageUrl;
    @SerializedName("checked_in") private List<String> checked_in;
    @SerializedName("promotions") private List<Promotion> promotionList;
    @SerializedName("review") private List<Review> reviewList;
    @SerializedName("type") private String type;

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

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getChecked_in() {
        return checked_in;
    }

    public void setChecked_in(List<String> checked_in) {
        this.checked_in = checked_in;
    }

    public List<Promotion> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
