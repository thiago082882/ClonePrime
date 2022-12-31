package com.example.primevideoclone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class AllCategory {

    @SerializedName("categoryid")
    @Expose
    private Integer categoryid;
    @SerializedName("categoryTitle")
    @Expose
    private String categoryTitle;
    @SerializedName("categoryItemList")
    @Expose
    private List<CategoryItemList> categoryItemList = null;

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public List<CategoryItemList> getCategoryItemList() {
        return categoryItemList;
    }

    public void setCategoryItemList(List<CategoryItemList> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }

}