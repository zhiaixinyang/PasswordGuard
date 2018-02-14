package com.mdove.passwordguard;

import android.app.Application;
import android.content.Context;

import com.mdove.passwordguard.greendao.DaoSession;
import com.mdove.passwordguard.greendao.utils.DaoManager;
import com.mdove.passwordguard.net.ApiServer;
import com.mdove.passwordguard.net.RetrofitUtil;
import com.mdove.passwordguard.net.UrlConstants;

/**
 * Created by MDove on 2018/2/9.
 */

public class App extends Application {
    public static Context mAppContext;
    private static DaoSession mDaoSession;
    private DaoManager mDaoManager;
    private static ApiServer mApiServer;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;

        mDaoManager = DaoManager.getInstance();
        mDaoManager.init(mAppContext);
        if (mDaoSession == null) {
            synchronized (App.class) {
                if (null == mDaoSession) {
                    mDaoSession = mDaoManager.getDaoMaster().newSession();
                }
            }
        }
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    public static ApiServer getApiServer() {
        if (mApiServer == null) {
            mApiServer = RetrofitUtil.create(UrlConstants.HOST_API_ONLINE, ApiServer.class);
        }
        return mApiServer;
    }

}
