package com.example.newsnow;

import android.util.Log;

import com.example.newsnow.models.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonExtraction {

    String json;

    public JsonExtraction(String json) {
        this.json = json;
        extract();
    }

    public void extract() {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray articlesArr = jsonObject.getJSONArray("articles");

            for (int i = 0; i < articlesArr.length(); i++) {
                JSONObject article = articlesArr.getJSONObject(i);

                String author = article.optString("author");
                String title = article.optString("title");
                String description = article.optString("excerpt");
                String urlLink = article.optString("link");
                String urlImg = article.optString("media");
                String date = article.getString("published_date");

                MainActivity.list.add(new News(author,title,description,urlLink,urlImg,date));

                Log.i("Author: ", author);
                Log.i("Title: ", title);
                Log.i("Description: ", description);
            }
            MainActivity.adapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
