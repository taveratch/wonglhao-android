package io.taweesoft.wonglhao.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TAWEESOFT on 4/14/16 AD.
 */
public class Element {
    @SerializedName("promotions") List<Promotion> promotionList;
    @SerializedName("bars") List<Bar> barList;
    public List<Promotion> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    public List<Bar> getBarList() {
        return barList;
    }

    public void setBarList(List<Bar> barList) {
        this.barList = barList;
    }
}
