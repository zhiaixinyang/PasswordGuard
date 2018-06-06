package com.mdove.passwordguard.main.newmain.timer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by MDove on 2018/6/5.
 */

public class TimerReceiver extends BroadcastReceiver {
    public static final String TIMER_ACTION = "mdove.action.timer.action";
    public static final String TIMER_ACTION_NOTIFICATION = "mdove.action.timer.action.notification";

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case TIMER_ACTION: {
                Vibrator vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
                long[] patter = {1000, 1000, 1000, 1000, 1000, 1000};
                vibrator.vibrate(patter, -1);
                String content = intent.getStringExtra(TimerConstant.TIMER_EXTRA);
                TimerNotificationHepler.sendTimerDailySelfNotification(context, content);
            }
        }
    }
}
