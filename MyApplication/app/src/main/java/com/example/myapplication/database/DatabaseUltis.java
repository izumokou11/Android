package com.example.myapplication.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.core.widget.ListViewAutoScrollHelper;

import com.example.myapplication.models.CategoryModel;
import com.example.myapplication.models.TopicModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DatabaseUltis {
    private static final String TAG = "DatabaseUltis";
    private final String TABLE_TOPIC = "tbl_topic";
    private final String TABLE_WORD = "tbl_word";
    private SQLiteDatabase sqLiteDatabase;
    private MyDatabase myDatabase;

    //singleton
    private static DatabaseUltis databaseUltis;

    public DatabaseUltis(Context context) {
        myDatabase = new MyDatabase(context);
    }

    public static DatabaseUltis getInstance(Context context) {
        if (databaseUltis == null) {
            databaseUltis = new DatabaseUltis(context);
        }
        return databaseUltis;
    }


    public List<TopicModel> getListTopic() {
        sqLiteDatabase = myDatabase.getReadableDatabase();
        List<TopicModel> topicModels = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_TOPIC, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            // read data
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String category = cursor.getString(4);
            String color = cursor.getString(5);
            String lastTime = cursor.getString(6);
            TopicModel topicModel = new TopicModel(id, name, category, color, lastTime);
            topicModels.add(topicModel);
            // next
            cursor.moveToNext();
        }
        Log.d(TAG, "getListTopic: " + topicModels);
        return topicModels;

    }

    public List<CategoryModel> getListCategory(List<TopicModel> topicModels) {
        List<CategoryModel> categoryModelList = new ArrayList<>();
        for (int i = 0; i < topicModels.size(); i = i + 5) {
            CategoryModel categoryModel = new CategoryModel(topicModels.get(i).getCategory(), topicModels.get(i).getColor());
            categoryModelList.add(categoryModel);
        }
        Log.d(TAG, "getListCategory: " + categoryModelList);
        return categoryModelList;
    }

    public HashMap<String, List<TopicModel>> getHashMapTopic(List<TopicModel> topicModels, List<CategoryModel> categoryModels) {
        HashMap<String, List<TopicModel>> hashMap = new HashMap<>();
        for (int i = 0; i < categoryModels.size(); i++) {
            int positionTopic = i * 5;
            hashMap.put(categoryModels.get(i).getName(),
                    topicModels.subList(positionTopic,positionTopic+5));
            Log.d(TAG, "getHashMapTopic: ----" + categoryModels.get(i).getName());
            Log.d(TAG, "getHashMapTopic: " + hashMap.get(categoryModels.get(i).getName()));
        }return hashMap;
    }

}
