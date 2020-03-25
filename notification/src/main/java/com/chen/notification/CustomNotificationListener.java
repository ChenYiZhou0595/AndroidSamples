package com.chen.notification;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class CustomNotificationListener extends NotificationListenerService {

    private static final String TAG = "CustomNotification";


    /**
     * 常用方法
     * cancelAllNotifications(); // 移除所有可移除的通知
     * cancelNotification(String key); // 移除指定key的通知，要求api21以上
     * cancelNotifications(String[] keys); // 移除指定数组内的所有key的通知，要求api21以上
     * getActiveNotifications()； // 获取通知栏上的所有通知，返回一个StatusBarNotification[]
     */

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        // 当连接成功时调用，一般在开启监听后会回调一次该方法
        Log.d(TAG, "onListenerConnected: ");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        // 当收到一条消息时回调，sbn里面带有这条消息的具体信息
        Log.d(TAG, "onNotificationPosted: ");
        // getPackageName(); //获取发送通知的应用程序包名
        // isClearable(); //通知是否可被清除
        // getId(); //获取通知id
        // getKey(); //获取通知的key
        // getPostTime(); //通知的发送时间
        // getNotification(); //获取Notification

        // Bundle extras = sbn.getNotification().extras;
        // String title = extras.getString(Notification.EXTRA_TITLE); //通知title
        // String content = extras.getString(Notification.EXTRA_TEXT); //通知内容
        // int smallIconId = extras.getInt(Notification.EXTRA_SMALL_ICON); //通知小图标id
        // Bitmap largeIcon =  extras.getParcelable(Notification.EXTRA_LARGE_ICON); //通知的大图标，注意和获取小图标的区别
        // PendingIntent pendingIntent = sbn.getNotification().contentIntent; //获取通知的PendingIntent

        // 在上面的代码中，注意获取通知小图标和大图标时的区别，小图标获取到的是一个int型的资源id
        // 而大图标是一个序列化后的bitmap，这点需要特别注意，还有，一些通知的大图标获取可能为null，需要注意空指针异常。
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        // 当移除一条消息的时候回调，sbn是被移除的消息
        Log.d(TAG, "onNotificationRemoved: ");
    }
}
