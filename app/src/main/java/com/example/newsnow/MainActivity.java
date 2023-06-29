package com.example.newsnow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.newsnow.adapter.NewsAdapter;
import com.example.newsnow.models.News;
import com.google.android.material.tabs.TabLayout;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {


    static final String API_KEY = "ADD_YOUR_OWN_NEWSCATCHER_API";
    static ArrayList<News> list;
    String userInput = "trending";

    static NewsAdapter adapter;

    ListView listView;
    TabLayout layout;

    URL url;
    HttpURLConnection con = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        list = new ArrayList<>();

        layout = findViewById(R.id.tabLayout);

        String apiUrl = "https://api.newscatcherapi.com/v2/latest_headlines";
        String url = apiUrl + "?topic=" + userInput + "&page_size=10&lang=en";
        Task task = new Task();
        task.execute(url);

        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                switch (position) {
                    case 0:
                        list.clear();
                        userInput = "trending";
                        Log.i("Trending","hi");
                        break;
                    case 1:
                        list.clear();
                        userInput = "business";
                        Log.i("business","hi");
                        break;
                    case 2:
                        list.clear();
                        userInput = "world";
                        Log.i("world","hi");
                        break;
                    case 3:
                        list.clear();
                        userInput = "finance";
                        break;
                    default:
                        break;
                }
                String apiUrl = "https://api.newscatcherapi.com/v2/latest_headlines";
                String url = apiUrl + "?topic=" + userInput + "&page_size=10&lang=en";
                Task task = new Task();
                task.execute(url);


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Do nothing when a tab is unselected
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Do nothing when a tab is reselected
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = (String) view.getTag();
                Intent intent = new Intent(getApplicationContext(), WebviewActivity.class);
                Log.d("MainActivity", "URL received: " + url);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });

        adapter = new NewsAdapter(this,R.layout.custom_list,list);

        listView.setAdapter(adapter);

    }

}