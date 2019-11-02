package com.ulan.app.quotes.utils.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import static com.ulan.app.quotes.utils.Constants.EXTRA_QUOTE_TEXT;
import static com.ulan.app.quotes.utils.Constants.NOTIFICATION_ID;

public class NotificationHelper {

    public static void setNotification(Context context, String sendMessage){
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(EXTRA_QUOTE_TEXT, sendMessage);
        PendingIntent penIntent = PendingIntent.getBroadcast(context,NOTIFICATION_ID, intent, PendingIntent.FLAG_ONE_SHOT);

        stopNotification(context, NOTIFICATION_ID);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + AlarmManager.INTERVAL_DAY + 7,
                AlarmManager.INTERVAL_DAY + 7, penIntent);
    }

    private static void stopNotification(Context context, int requestCode){
        try{
            Intent notifIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent pendIntent = PendingIntent
                    .getBroadcast(context, requestCode, notifIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager2 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager2.cancel(pendIntent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
