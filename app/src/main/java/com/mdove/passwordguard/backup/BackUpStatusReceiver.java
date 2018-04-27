package com.mdove.passwordguard.backup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.mdove.passwordguard.utils.ToastHelper;

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
                ToastHelper.shortToast("备份失败");
                break;
            }
            case ACTION_BACK_UP_START: {
                ToastHelper.shortToast("开始备份");
                break;
            }
            case ACTION_BACK_UP_SUC: {
                ToastHelper.shortToast("备份成功");
                break;
            }
            case ACTION_BACK_UP_HAS_EXIST: {
                ToastHelper.shortToast("已存在备份文件");
                break;
            }
            default: {
                break;
            }
        }
    }
}
