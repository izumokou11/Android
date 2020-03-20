package com.example.add;
import  android.content.Context;
import android.view.View;


public class CustomView extends View {
    public CustomView (Context context){super(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int width;
        int height;
        width = widthSize;
        height = widthSize/2;
        setMeasuredDimension(width,height);
    }
}
