package com.example.ruixu.imagegallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imgList);
        Intent intent = getIntent();
        String folderName = intent.getExtras().getString("FolderName");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        List<CreateList> createLists = prepareData(folderName);
        MyAdapter adapter = new MyAdapter(getApplicationContext(), createLists,2);
        recyclerView.setAdapter(adapter);

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

