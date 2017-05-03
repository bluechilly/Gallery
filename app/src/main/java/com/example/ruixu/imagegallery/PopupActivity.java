package com.example.ruixu.imagegallery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rui on 5/2/2017.
 */

public class PopupActivity extends Activity {
    PopAdapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels, height = dm.heightPixels;
//        getWindow().setLayout((int)(width*.8), (int)(height*0.6));


        Intent intent = getIntent();
        String folderName = intent.getExtras().getString("FolderName");

//
//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.popup_list);
//        recyclerView.setHasFixedSize(true);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(layoutManager);
//        List<CreateList> createLists = prepareData(folderName);
//        MyAdapter adapter = new MyAdapter(getApplicationContext(), createLists, 3);
//        recyclerView.setAdapter(adapter);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels, height = dm.heightPixels;
        getWindow().setLayout(width, width);

        adapter = new PopAdapter(this, prepareData(folderName));
        viewPager = (ViewPager)findViewById(R.id.popup_list);
        viewPager.setAdapter(adapter);


    }


    private List<CreateList> prepareData(String folderName) {

        List<CreateList> cateList = new ArrayList<CreateList>();
        String[] list_image = null;

        try {
            list_image = this.getAssets().list(folderName);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        for (int i = 0; i < list_image.length; ++i) {
            InputStream open = null;
            try {
                String temp = folderName + '/' + list_image[i];
                open = this.getAssets().open(temp);
                Bitmap bitmap =ImageUtil.rotateImage(open);
                CreateList item = new CreateList();
                item.setImage_title(list_image[i]);
                item.setBitmap(bitmap);
                cateList.add(item);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return cateList;
    }
}
