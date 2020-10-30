package com.project.matchingapp3.task;

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

public class RestAPITask extends AsyncTask<String, Object, String[]> {
    final static String ip ="172.30.1.42"; // IP - 집
    //final static String ip ="10.100.102.15"; // IP - 학원
    //final static String ip ="localhost"; // IP - 학원
    private String serverUrl = "http://"+ip+":8000/"; // 연결할 서버주소
    private String reqUrl = "";
    private String method = "";
    private String contentType = "application/json; charset=utf-8";
    private String authorization = "";
    private String reqData,resData = "";

    public RestAPITask(String url){
        this.reqUrl = url;
    }

    public RestAPITask(String reqUrl, String authorization){
        this.reqUrl = reqUrl;
        this.authorization = authorization;
    }

    @Override
    protected String[] doInBackground(String... json) {
        if(reqUrl.equals("login")){
            serverUrl += reqUrl;
            method = "POST";
            reqData = json[0];
        }else if(reqUrl.equals("join")){
            serverUrl += reqUrl;
            method = "POST";
            reqData = json[0];
        }else if(reqUrl.equals("user/appmain")){
            Log.d("test","여까지옴");
            serverUrl += reqUrl;
            method = "GET";
            reqData = json[0];
        }

        try {
            String str = "";
            URL url = new URL(serverUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", authorization);   //토큰
            conn.setRequestProperty("Content-Type", contentType);
            conn.setRequestMethod(method);
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            osw.write(reqData);
            osw.flush();
            osw.close();

            if(conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();

                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                resData = buffer.toString();
                //login요청일 때만 map에 헤더정보 넣기.
                if(reqUrl.equals("login")){
                    Map m = conn.getHeaderFields();
                    if(m.containsKey("Authorization")) {
                        Collection c =(Collection)m.get("Authorization");
                        for(Iterator i = c.iterator(); i.hasNext(); ) {
                            authorization = (String)i.next();
                        }
                        Log.d("test-받은header 토큰값", authorization);
                    }
                    Log.d("test-request body data", reqData);
                    Log.d("test-response body data", resData);
                    return new String[]{resData, authorization};
                }
            } else {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();

                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }

                resData = buffer.toString();

                Log.d("응답 코드", conn.getResponseCode()+"에러");    //응답코드받기
                Log.d("응답 메시지", conn.getResponseMessage());    //응답메시지
                Log.d("response body data", resData);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("test-request body data", reqData);
        Log.d("test-response body data", resData);

        return new String[]{resData};
    }

}
