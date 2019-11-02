package com.lessons.firebase.quotes.utils.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static com.lessons.firebase.quotes.utils.Constants.EXTRA_QUOTE_TEXT;
import static com.lessons.firebase.quotes.utils.Constants.NOTIFICATION_ID;

public class NotificationHelper {

    public static void setNotification(Context context, String sendMessage){
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(EXTRA_QUOTE_TEXT, sendMessage);
        PendingIntent penIntent = PendingIntent.getBroadcast(context,NOTIFICATION_ID, intent, PendingIntent.FLAG_ONE_SHOT);

        stopNotification(context, NOTIFICATION_ID);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + AlarmManager.INTERVAL_DAY + 7,
                AlarmManager.INTERVAL_DAY + 7, penIntent);
        Toast.makeText(context, "Alarm working after 3 days", Toast.LENGTH_SHORT).show();
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
