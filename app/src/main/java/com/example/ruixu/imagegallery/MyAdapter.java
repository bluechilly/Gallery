package com.example.ruixu.imagegallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;


/**
 * Created by rui on 5/2/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<CreateList> galleryList;
    private Context context;
    private int indicator;

    public MyAdapter(Context context, List<CreateList> galleryList, int indicator) {
        this.galleryList = galleryList;
        this.context = context;
        this.indicator = indicator;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = null;
        if(indicator == 1) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cover_item, viewGroup, false);
        } else if(indicator == 2) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_item, viewGroup, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {
        final String  title = galleryList.get(i).getImage_title();
        viewHolder.title.setText(title);
        if(indicator == 1) {
            viewHolder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("FolderName", title);
                    context.startActivity(intent);
                }
            });
            viewHolder.img.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(context, PopupActivity.class);
                    intent.putExtra("FolderName", title);
                    context.startActivity(intent);
                    return true;
                }
            });
        }

        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewHolder.img.setImageBitmap((galleryList.get(i).getbitMap()));
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView img;
        public ViewHolder(View view) {
            super(view);
            if(indicator == 1) {
                title =(TextView)view.findViewById(R.id.cover_title);
                img = (ImageView)view.findViewById(R.id.cover_img);
            }else if(indicator == 2) {
                title = (TextView)view.findViewById(R.id.cell_title);
                img = (ImageView) view.findViewById(R.id.cell_img);
            }
        }
    }
}