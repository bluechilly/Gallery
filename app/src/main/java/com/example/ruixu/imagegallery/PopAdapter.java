package com.example.ruixu.imagegallery;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by rui on 5/2/2017.
 */

public class PopAdapter extends PagerAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<CreateList> galleryList;

    public PopAdapter(Context context, List<CreateList> galleryList) {
        this.context = context;
        this. galleryList = galleryList;
    }
    @Override
    public int getCount() {
        return galleryList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.popup_item, container, false);
        ImageView img = (ImageView)v.findViewById(R.id.popup_img);
        img.setImageBitmap(galleryList.get(position).getbitMap());
        container.addView(v);
        return v;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
}
