package com.mdove.passwordguard.backup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * Created by MDove on 2018/4/16.
 */

public class BackUpStatusReceiver extends BroadcastReceiver {
    public static final String ACTION_BACK_UP_SUC = "mdove.action.backup.suc";
    public static final String ACTION_BACK_UP_ERROR = "mdove.action.backup.error";
    public static final String ACTION_BACK_UP_START = "mdove.action.backup.start";
    public static final String ACTION_BACK_UP_HAS_EXIST = "mdove.action.backup.has.exist";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (TextUtils.isEmpty(intent.getAction())) {
            return;
        }

        switch (intent.getAction()) {
            case ACTION_BACK_UP_ERROR: {
                break;
            }
            case ACTION_BACK_UP_START: {
                break;
            }
            case ACTION_BACK_UP_SUC: {
                break;
            }
            case ACTION_BACK_UP_HAS_EXIST: {
                break;
            }
            default: {
                break;
            }
        }
    }
}
