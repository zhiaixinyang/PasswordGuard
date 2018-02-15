package com.mdove.passwordguard.manager;

import com.mdove.passwordguard.config.AppConfig;
import com.mdove.passwordguard.utils.DateUtil;

/**
 * Created by MDove on 2018/2/15.
 */

public class UpdateStatusManager {
    public static boolean isShowUpdateDialog() {
        if (AppConfig.isShowUpdateDialogEnable()) {
            AppConfig.setIsShowUpdateDialogEnable(false);
            return true;
        }
        long curTime = System.currentTimeMillis();
        if (AppConfig.getAppOrderTodayTime()==0){
            AppConfig.setAppOrderTodayTime(curTime);
            return true;
        }
        boolean isSame= DateUtil.isSameDay(curTime,AppConfig.getAppOrderTodayTime());
        if (!isSame){
            AppConfig.setAppOrderTodayTime(curTime);
            return true;
        }
        return false;
    }
}
