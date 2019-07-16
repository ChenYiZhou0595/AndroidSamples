package com.chen.fragmentlifecycle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    FragmentManager supportFragmentManager;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().add(R.id.fl_content, FirstFragment.newInstance(), "CurrentFragment").commit();
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supportFragmentManager.beginTransaction()
                        .remove(supportFragmentManager.findFragmentByTag("CurrentFragment"))
                        .add(R.id.fl_content, FirstFragment.newInstance(), "CurrentFragment")
                        .commit();
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supportFragmentManager.beginTransaction()
                        .remove(currentFragment)
                        .add(R.id.fl_content, SecondFragment.newInstance())
                        .commit();
            }
        });
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supportFragmentManager.beginTransaction()
                        .remove(currentFragment)
                        .add(R.id.fl_content, ThirdFragment.newInstance())
                        .commit();
            }
        });
    }
}
