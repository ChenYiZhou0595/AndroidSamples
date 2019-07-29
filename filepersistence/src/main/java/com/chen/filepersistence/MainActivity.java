package com.chen.filepersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etContent = findViewById(R.id.et_content);
        String content = load();
        if (!TextUtils.isEmpty(content)) {
            etContent.setText(content);
            etContent.setSelection(content.length());
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show();
        }
    }

    private String load() {
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder content = new StringBuilder();
        try {
            fileInputStream = openFileInput("data");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String content = etContent.getText().toString();
        save(content);
    }

    private void save(String content) {
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = openFileOutput("data", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
