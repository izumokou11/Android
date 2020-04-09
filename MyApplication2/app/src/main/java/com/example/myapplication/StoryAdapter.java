package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class StoryAdapter extends ArrayAdapter<ItemModels> {
    List<ItemModels> arrItem;
    private int resouce;

    public StoryAdapter(@NonNull Context context, int resouce, @NonNull List<ItemModels> objects) {
        super(context, resouce, objects);
        this.arrItem = objects;
        this.resouce = resouce;
    }

    @SuppressLint("Check")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_story, parent, false);
            viewHolder.ivStory = convertView.findViewById(R.id.iv_story);
            viewHolder.tvTitle = convertView.findViewById(R.id.tv_title);
            viewHolder.tvAuthor = convertView.findViewById(R.id.tv_author);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ItemModels itemModel = arrItem.get(position);
        Glide.with(parent.getContext()).load(
                DatabaseUtils.getInstance(parent.getContext()).getListTopic().get(position).getImage()
        ).into(viewHolder.ivStory);
        viewHolder.tvTitle.setText(DatabaseUtils.getInstance(parent.getContext()).getListTopic().get(position).getTitle());
        viewHolder.tvAuthor.setText(DatabaseUtils.getInstance(parent.getContext()).getListTopic().get(position).getAuthor());
        return convertView;


    }
    public class ViewHolder {
        ImageView ivStory;
        TextView tvTitle;
        TextView tvAuthor;
    }
}
