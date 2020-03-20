package com.example.add;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabAdd;
    ScrollView scView;
    LinearLayout llScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fabAdd = findViewById(R.id.fab_add);
        scView = findViewById(R.id.sc_view);
        llScroll = findViewById(R.id.ll_scroll);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llScroll.addView(createImageView(MainActivity.this));
                scView.post(new Runnable() {
                    @Override
                    public void run() {
                        scView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });
    }

    public CustomView createImageView(Context context) {
        CustomView imageView = new CustomView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 16);
        imageView.setLayoutParams(layoutParams);
        imageView.setBackgroundResource(randomImage());
        return imageView;
    }

    public int randomImage() {
        Random random = new Random();
        int rdImage = random.nextInt(5) + 1;
        switch (rdImage) {
            case 1:
                return R.drawable.c_1;
            case 2:
                return R.drawable.c_2;
            case 3:
                return R.drawable.c_3;
            case 4:
                return R.drawable.c_4;
            case 5:
                return R.drawable.c_5;
            case 6:
                return R.drawable.c_6;
            case 7:
                return R.drawable.c_7;
        }
        return 0;
    }
}
