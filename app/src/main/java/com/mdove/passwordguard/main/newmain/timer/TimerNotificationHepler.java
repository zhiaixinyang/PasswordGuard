package com.mdove.passwordguard.main.newmain.timer;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.mdove.passwordguard.R;

/**
 * Created by MDove on 2018/6/5.
 */

public class TimerNotificationHepler {

    public static void sendTimerDailySelfNotification(@NonNull Context context,String content) {
        int notificationId = 0;
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(TimerReceiver.TIMER_ACTION_NOTIFICATION);
        intent.setComponent(new ComponentName(context, TimerReceiver.class));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent deleteIntent = new Intent(TimerReceiver.TIMER_ACTION_NOTIFICATION);
        deleteIntent.setComponent(new ComponentName(context, TimerReceiver.class));
        PendingIntent deletePendingIntent = PendingIntent.getBroadcast(context, notificationId, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_normal);
        remoteViews.setImageViewResource(R.id.cover, R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.title_text, "闹钟计划");
        remoteViews.setTextViewText(R.id.content_text, "是时候完成这个任务："+content);
        remoteViews.setImageViewResource(R.id.play_btn, R.mipmap.ic_delete);

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("闹钟计划")
                .setContentText("是时候完成这个任务："+content)
                .setContentIntent(pendingIntent)
                .setDeleteIntent(deletePendingIntent)
                .setContent(remoteViews)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true);

        nm.notify(notificationId, builder.build());
    }
}
