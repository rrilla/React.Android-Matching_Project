package com.project.matchingapp3.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageTask extends AsyncTask<String, Integer, Bitmap>{

    //final static String ip ="172.30.1.42"; // IP - 집
    final static String ip ="10.100.102.15"; // IP - 학원
    private String serverUrl = "http://"+ip+":8000/image/"; // 연결할 서버주소
    private Bitmap bmImg;

    @Override
    protected Bitmap doInBackground(String... strings) {
        try{
            URL myFileUrl = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();

            bmImg = BitmapFactory.decodeStream(is);

        }catch(IOException e){
            e.printStackTrace();
        }
        return bmImg;
    }

}
