package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    StoryAdapter adapter;
    ListView listView;
    public static ReadingStory readingStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readingStory = new ReadingStory();
        listView = findViewById(R.id.lv_list);
        adapter = new StoryAdapter(MainActivity.this, R.layout.list_story, DatabaseUtils.getInstance(this).getListTopic());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ReadingStory.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }
}
