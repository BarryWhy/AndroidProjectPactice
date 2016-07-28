package com.dell.androidprojectpactice.ui.mainimageslide;

import me.wangyuwei.banner.BannerEntity;

/**
 * Created by dell on 2016/7/20.
 */
public class BannerImage extends BannerEntity{
    private String imageUrl;
    private String title;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
