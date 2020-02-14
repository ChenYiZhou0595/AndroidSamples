package com.chen.okhttp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                long t1 = System.nanoTime();
                Log.d(TAG, "request = " + request.toString());

                Response response = chain.proceed(request);

                long t2 = System.nanoTime();
                //1e6d = 10的6的方
                Log.d(TAG, "time cost = " + (t2 - t1) / 1e6d + "ms \n response = " + response.toString());

                return response;
            }
        });
        OkHttpClient okHttpClient = builder.build();
        Request request = new Request.Builder().url("http://www.baidu.com").build();
        // 同步方式
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Response response = okHttpClient.newCall(request).execute();
//                    try {
//                        Log.i(TAG, response.body().string());
//                    } catch (Throwable t) {
//                        t.printStackTrace();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        // 异步方式
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    Log.i(TAG, "onResponse: " + response.body().string());
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        });
    }
}
