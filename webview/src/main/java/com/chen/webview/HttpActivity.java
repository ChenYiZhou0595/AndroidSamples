package com.chen.webview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "HttpActivity";

    private Button btnSendRequestWithHttpURLConnection;
    private Button btnSendRequestWithOkHttp;
    private Button btnXMLPull;
    private Button btnXMLSAX;
    private TextView tvResponseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        btnSendRequestWithHttpURLConnection = findViewById(R.id.btn_send_request_with_http_url_connection);
        btnSendRequestWithHttpURLConnection.setOnClickListener(this);

        btnSendRequestWithOkHttp = findViewById(R.id.btn_send_request_with_OkHttp);
        btnSendRequestWithOkHttp.setOnClickListener(this);

        btnXMLPull = findViewById(R.id.btn_xml_pull);
        btnXMLPull.setOnClickListener(this);

        btnXMLSAX = findViewById(R.id.btn_xml_sax);
        btnXMLSAX.setOnClickListener(this);

        tvResponseText = findViewById(R.id.tv_response_text);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_request_with_http_url_connection:
                sendRequestWithHttpURLConnection();
                break;
            case R.id.btn_send_request_with_OkHttp:
                sendRequestWithOkHttp();
                break;
            case R.id.btn_xml_pull:
                responseXMLPull();
                break;
        }
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    // 连接超时
                    httpURLConnection.setConnectTimeout(8000);
                    // 读取超时
                    httpURLConnection.setReadTimeout(8000);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }
                    showResponse(response.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://www.baidu.com")
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String responseString = response.body().string();
                    showResponse(responseString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void responseXMLPull() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:88/data.xml")
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String responseString = response.body().string();
                    parseXMLWithPull(responseString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String responce) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvResponseText.setText(responce);
            }
        });
    }

    private void parseXMLWithPull(String xmlString) {
        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlString));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    // 开始解析某个节点
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    // 完成解析某个节点
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodeName)) {
                            Log.d(TAG, "id is " + id);
                            Log.d(TAG, "name is " + name);
                            Log.d(TAG, "version is " + version);
                        }
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
