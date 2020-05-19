package com.chen.accessibilityservice;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

/**
 * <pre>
 *     author : chenyizhou
 *     e-mail : chenyizhou0595@qq.com
 *     time   : 2020/04/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MyAccessibilityService extends AccessibilityService {


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        performGlobalAction(GLOBAL_ACTION_RECENTS);
    }

    @Override
    public void onInterrupt() {

    }
}
