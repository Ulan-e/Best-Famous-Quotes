package com.lessons.firebase.quotes.utils.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.core.app.NotificationCompat;

import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.ui.mainactivity.MainActivity;
import com.lessons.firebase.quotes.utils.Constants;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String dataFromActivity = intent.getStringExtra(Constants.EXTRA_QUOTE_TEXT);
        Intent intentMain = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentMain,0);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder
                .setSmallIcon(R.mipmap.main_icon)
                .setContentTitle("Quote of day")
                .setContentText(dataFromActivity)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(dataFromActivity))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(534232, notification);
    }
}
