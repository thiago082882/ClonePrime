package com.example.primevideoclone.model;

public class BannerMovies {


    Integer bannerCategoryid;
    Integer id;
    String movieName;
    String imageUrl;
     String fileUrl;


    public Integer getBannerCategoryid() {
        return bannerCategoryid;
    }

    public void setBannerCategoryid(Integer bannerCategoryid) {
        this.bannerCategoryid = bannerCategoryid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
