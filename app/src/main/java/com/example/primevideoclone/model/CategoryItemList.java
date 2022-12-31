package com.example.primevideoclone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CategoryItemList {

    @SerializedName("fileUri")
    @Expose
    private String fileUri;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("movieName")
    @Expose
    private String movieName;

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

}