package com.example.matchingapp.task;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class RestAPITask extends AsyncTask<String, Object, Object> {
    final static String ip ="172.30.1.42"; // IP - 집
    //final static String ip ="192.168.0.27"; // IP - 학원
    private String serverUrl = "http://"+ip+":8000/"; // 연결할 서버주소
    private String url;
    private String method;
    private String contentType = "application/json; charset=utf-8";
    private String Authorization = "";
    private String reqData,resData;

    public RestAPITask(String url){
        this.url = url;
    }

    @Override
    protected String doInBackground(String... json) {
        if(url.equals("login")){
            serverUrl += url;
            method = "POST";
            reqData = json[0];
        }else if(url.equals("join")){
            serverUrl += url;
            method = "POST";
            reqData = json[0];
        }

        try {
            Log.d("test2", "ok");
            String str = "";
            URL url = new URL(serverUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", Authorization);   //토큰
            conn.setRequestProperty("Content-Type", contentType);
            conn.setRequestMethod(method);
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            osw.write(reqData);
            osw.flush();
            osw.close();
            Log.d("test3", "ok");

            if(conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }

                //response header 토큰값 추출
                Map m = conn.getHeaderFields();
                if(m.containsKey("Authorization")) {
                    Collection c =(Collection)m.get("Authorization");
                    for(Iterator i = c.iterator(); i.hasNext(); ) {
                        Authorization = (String)i.next();
                    }
                }
                Log.d("test-받은header 토큰값", Authorization);
                resData = buffer.toString();
            } else {
                StringBuffer buffer = new StringBuffer();
                buffer.append(str);
                Log.i("응답 코드", conn.getResponseCode()+"에러");    //응답코드받기
                Log.i("응답 메시지", conn.getResponseMessage());    //응답코드받기
                Log.d("서버 메시지",str);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("test-보낸값", json[0]);
        Log.d("test-받은값", resData);
        return resData;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}
