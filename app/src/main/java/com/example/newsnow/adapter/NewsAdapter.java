package com.example.newsnow.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.newsnow.R;
import com.example.newsnow.models.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    private Context mContext;
    private int mResource;

    public NewsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<News> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView imageView = convertView.findViewById(R.id.ivImage);
        TextView author = convertView.findViewById(R.id.tvAuthor);
        TextView title = convertView.findViewById(R.id.tvTitle);
        TextView desc = convertView.findViewById(R.id.tvDesc);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        RelativeLayout layout = convertView.findViewById(R.id.layout);

        author.setText(getItem(position).getAuthor());
        title.setText(getItem(position).getTitle());
        desc.setText(getItem(position).getDesc());
        tvDate.setText(getItem(position).getPublishedDate());
        layout.setTag(getItem(position).getUrl());


        Picasso.get().load(getItem(position).getImgUrl()).into(imageView);

        return convertView;
    }
}
