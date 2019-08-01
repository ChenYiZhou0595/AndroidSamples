package com.chen.webview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
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
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "HttpActivity";

    private Button btnSendRequestWithHttpURLConnection;
    private Button btnSendRequestWithOkHttp;
    private Button btnXMLPull;
    private Button btnXMLSAX;
    private Button btnJSONJSONObject;
    private Button btnJSONGSON;
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

        btnJSONJSONObject = findViewById(R.id.btn_json_json_object);
        btnJSONJSONObject.setOnClickListener(this);

        btnJSONGSON = findViewById(R.id.btn_json_gson);
        btnJSONGSON.setOnClickListener(this);

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
            case R.id.btn_xml_sax:
                responseXMLSAX();
                break;
            case R.id.btn_json_json_object:
                responseJSONJSONObject();
                break;
            case R.id.btn_json_gson:
                responseJSONGSON();
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

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvResponseText.setText(response);
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

    private void responseXMLSAX() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.0.109:88/data.xml")
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String responseString = response.body().string();
                    parseXMLWithSAX(responseString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseXMLWithSAX(String xmlString) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(new ContentHandler());
            xmlReader.parse(new InputSource(new StringReader(xmlString)));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void responseJSONJSONObject() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:88/json.json")
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String responseString = response.body().string();
                    parseJSONWithJSONObject(responseString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject(String jsonString) {
        try {
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String id = object.getString("id");
                String name = object.getString("name");
                String version = object.getString("version");
                Log.d(TAG, "id is " + id);
                Log.d(TAG, "name is " + name);
                Log.d(TAG, "version is " + version);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void responseJSONGSON() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:88/json.json")
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String responseString = response.body().string();
                    parseJSONWithGSON(responseString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonString) {
        Gson gson = new Gson();
        List<App> list = gson.fromJson(jsonString, new TypeToken<List<App>>() {
        }.getType());
        for (App app : list) {
            Log.d(TAG, "id is " + app.getId());
            Log.d(TAG, "name is " + app.getName());
            Log.d(TAG, "version is " + app.getVersion());
        }
    }
}
