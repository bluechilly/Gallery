package com.example.ruixu.imagegallery;

import android.graphics.Bitmap;

/**
 * Created by rui on 5/2/2017.
 */

public class CreateList {

    private String image_title;
    private Bitmap bitmap;

    public String getImage_title() {
        return image_title;
    }

    public void setImage_title(String android_version_name) {
        this.image_title = android_version_name;
    }

    public Bitmap getbitMap() {
        return  bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}