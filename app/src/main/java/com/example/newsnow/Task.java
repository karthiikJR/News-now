package com.example.newsnow;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Task extends AsyncTask<String,Void,String> {

    URL url;
    HttpURLConnection con = null;

    @Override
    protected String doInBackground(String... urls) {
        try {
            String temp="";
            url = new URL(urls[0]);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("x-api-key", MainActivity.API_KEY);
            InputStream in = con.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while (data!=-1) {
                char curr = (char) data;
                temp+=curr;
                data = reader.read();
            }
            Log.i("Data",temp);
            return temp;
        } catch (Exception e) {
            Log.i("error",e.toString());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s!=null) {
            Log.i("result", s);
            JsonExtraction js = new JsonExtraction(s);

            // After the execution setting up all the values
        }
        else
            Log.i("null","ok null");
    }
}
