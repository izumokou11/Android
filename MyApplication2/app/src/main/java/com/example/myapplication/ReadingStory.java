package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class ReadingStory extends AppCompatActivity {
    @BindView(R.id.iv_story_rd)
    ImageView ivStoryRD;
    @BindView(R.id.tv_story_name_rd)
    TextView tvStoryNameRD;
    @BindView(R.id.tv_author_rd)
    TextView tvAuthorRD;
    @BindView(R.id.tv_read_story)
    TextView tvReadStory;
    @BindView(R.id.tv_description_rd)
    TextView tvDescriptionRD;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_story);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        getData(position);
    }

    private void getData(int position) {
        Glide.with(this).load(DatabaseUtils.getInstance(this).getListTopic().get(position).getImage()).into(ivStoryRD);
        tvStoryNameRD.setText(DatabaseUtils.getInstance(this).getListTopic().get(position).getTitle());
        tvAuthorRD.setText(DatabaseUtils.getInstance(this).getListTopic().get(position).getAuthor());
        tvDescriptionRD.setText(DatabaseUtils.getInstance(this).getListTopic().get(position).getDescription());
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
