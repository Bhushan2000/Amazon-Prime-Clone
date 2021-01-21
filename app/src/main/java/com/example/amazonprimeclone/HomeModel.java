package com.example.amazonprimeclone;

public class HomeModel {

    // horizontal
    private int imageUrl;
    private String title;
    private  int viewType;

    public HomeModel(int imageUrl, String title, int viewType) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.viewType = viewType;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    // slide
    private int slideUrl;

    public HomeModel(int viewType, int slideUrl) {
        this.viewType = viewType;
        this.slideUrl = slideUrl;
    }

    public int getSlideUrl() {
        return slideUrl;
    }

    public void setSlideUrl(int slideUrl) {
        this.slideUrl = slideUrl;
    }

    ;
}
