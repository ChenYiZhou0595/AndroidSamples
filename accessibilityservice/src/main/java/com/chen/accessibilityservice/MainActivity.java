package com.chen.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvMyAccessibilityServiceStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMyAccessibilityServiceStatus = findViewById(R.id.tvMyAccessibilityServiceStatus);

        findViewById(R.id.btnGoAccessibilityService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isServiceEnable(this, MyAccessibilityService.class.getSimpleName())) {
            tvMyAccessibilityServiceStatus.setText("MyAccessibilityService 已开启");
        } else {
            tvMyAccessibilityServiceStatus.setText("MyAccessibilityService 未开启");
        }
    }

    public static boolean isServiceEnable(Context context, String className) {
        String name = context.getPackageName() + "/." + className;
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        List<AccessibilityServiceInfo> accessibilityServices = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);
        for (AccessibilityServiceInfo info : accessibilityServices) {
            if (info.getId().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
