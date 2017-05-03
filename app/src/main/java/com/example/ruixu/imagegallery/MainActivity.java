package com.example.ruixu.imagegallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String[] folders = {"Animals", "Architecture", "Food", "Posters", "Scenery"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.coverList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        List<CreateList> createLists = prepareData();
        //indicator == 1: means this adapter works for MainActivity
        MyAdapter adapter = new MyAdapter(getApplicationContext(), createLists, 1);

        recyclerView.setAdapter(adapter);

    }
    //get first image from each folder
    private List<CreateList> prepareData() {

        List<CreateList> cateList = new ArrayList<CreateList>();
        String[] list_image = null;
        InputStream open = null;
        try {
            for(int i = 0; i < folders.length; i++) {
                try {
                    list_image = this.getAssets().list(folders[i]);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                String temp = folders[i] + '/' + list_image[0];

                open = this.getAssets().open(temp);
                Bitmap bitmap =ImageUtil.rotateImage(open);

                CreateList item = new CreateList();

                item.setImage_title(folders[i]);
                item.setBitmap(bitmap);

                cateList.add(item);
            }
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
        return cateList;
    }
}
