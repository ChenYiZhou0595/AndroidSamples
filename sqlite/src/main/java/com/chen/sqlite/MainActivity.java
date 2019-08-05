package com.chen.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        Button btnCreateDatabase = findViewById(R.id.btn_create_database);
        btnCreateDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
            }
        });

        Button btnAddData = findViewById(R.id.btn_add_data);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "The Da Vinci Code");
                contentValues.put("author", "Dan Brown");
                contentValues.put("pages", 454);
                contentValues.put("price", 16.9);
                sqLiteDatabase.insert("Book", null, contentValues);
                contentValues.clear();
                contentValues.put("name", "The Lost Symbol");
                contentValues.put("author", "Dan Brown");
                contentValues.put("pages", 510);
                contentValues.put("price", 17.8);
                sqLiteDatabase.insert("book", null, contentValues);
            }
        });

        Button btnUpdateData = findViewById(R.id.btn_update_data);
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("price", 22.2);
                sqLiteDatabase.update("book", contentValues, "name = ?", new String[]{"The Da Vinci Code"});
            }
        });

        Button btnDeleteData = findViewById(R.id.btn_delete_data);
        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                sqLiteDatabase.delete("book", "pages > ?", new String[]{"500"});
            }
        });

        Button btnQueryData = findViewById(R.id.btn_query_data);
        btnQueryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                Cursor cursor = sqLiteDatabase.query("book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "name : " + name);
                        Log.d(TAG, "author : " + author);
                        Log.d(TAG, "pages : " + pages);
                        Log.d(TAG, "price : " + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

        Button btnCreateDatabase2 = findViewById(R.id.btn_create_database2);
        btnCreateDatabase2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connector.getDatabase();
            }
        });

        Button btnAddData2 = findViewById(R.id.btn_add_data2);
        btnAddData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(456);
                book.setPrice(19.2);
                book.setPress("Unknown");
                book.save();
            }
        });

        Button btnUpdateData2 = findViewById(R.id.btn_update_data2);
        btnUpdateData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(456);
                book.setPrice(19.2);
                book.setPress("Unknown");
                book.save();
                book.setPress("UnKnown Again");
                book.save();
            }
        });

        Button btnUpdateData21 = findViewById(R.id.btn_update_data2_1);
        btnUpdateData21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("ChenYiZhou");
                book.updateAll("press = ?", "UnKnown Again");
            }
        });

        Button btnDeleteData2 = findViewById(R.id.btn_delete_data2);
        btnDeleteData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class, "pages > ?", "400");
            }
        });

        Button btnQueryData2 = findViewById(R.id.btn_query_data2);
        btnQueryData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> bookList = LitePal.findAll(Book.class);
                for (Book book : bookList) {
                    Log.d(TAG, "name : " + book.getName());
                    Log.d(TAG, "author : " + book.getAuthor());
                    Log.d(TAG, "pages : " + book.getPages());
                    Log.d(TAG, "price : " + book.getPrice());
                }
            }
        });
    }
}
