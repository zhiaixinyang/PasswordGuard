package com.mdove.passwordguard.backup;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.DailyPlanDao;
import com.mdove.passwordguard.greendao.DailySelfDao;
import com.mdove.passwordguard.greendao.DeleteSelfTaskDao;
import com.mdove.passwordguard.greendao.DeletedDailySelfDao;
import com.mdove.passwordguard.greendao.DeletedPasswordDao;
import com.mdove.passwordguard.greendao.GroupInfoDao;
import com.mdove.passwordguard.greendao.PasswordDao;
import com.mdove.passwordguard.greendao.SelfTaskDao;
import com.mdove.passwordguard.greendao.SucSelfTaskDao;
import com.mdove.passwordguard.greendao.entity.DailyPlan;
import com.mdove.passwordguard.utils.AlertDialogUtils;
import com.mdove.passwordguard.utils.IOUtils;
import com.mdove.passwordguard.utils.ToastHelper;
import com.mdove.passwordguard.utils.log.LogUtils;

import org.greenrobot.greendao.AbstractDao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by MDove on 2018/4/16.
 */

public class BackUpService extends IntentService {
    public static final String ACTION_BACK_UP_SERVICE_START = "action_back_up_service_start";
    private static final String DEFAULT_FILENAME = "passwordguard.db";

    private BackUpStatusReceiver mReceiver;

    public static void start(Context context) {
        Intent start = new Intent(context, BackUpService.class);
        start.setAction(ACTION_BACK_UP_SERVICE_START);
        context.startService(start);
    }

    public BackUpService() {
        super("BackUpService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mReceiver = new BackUpStatusReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BackUpStatusReceiver.ACTION_BACK_UP_ERROR);
        filter.addAction(BackUpStatusReceiver.ACTION_BACK_UP_START);
        filter.addAction(BackUpStatusReceiver.ACTION_BACK_UP_SUC);
        registerReceiver(mReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        switch (action) {
            case ACTION_BACK_UP_SERVICE_START: {
                backupStart();
                break;
            }
            default: {
                break;
            }
        }
    }

    private void backupStart() {
        File file = new File(Environment.getExternalStorageDirectory(), DEFAULT_FILENAME);
        if (!file.exists()) {
            Intent startBackUp = new Intent(BackUpStatusReceiver.ACTION_BACK_UP_START);
            sendBroadcast(startBackUp);
            copyRawDBToApkDb(this,getDatabasePath("PasswordGuard.db").getPath(),
                    Environment.getExternalStorageDirectory().getPath(), DEFAULT_FILENAME);
        } else {
            Intent hasBackUp = new Intent(BackUpStatusReceiver.ACTION_BACK_UP_HAS_EXIST);
            sendBroadcast(hasBackUp);
        }
    }

    public static boolean copyRawDBToApkDb(Context context,String innerPath, String apkDbPath, String dbName) {
        boolean b;

        File f = new File(apkDbPath);
        if (!f.exists()) {
            f.mkdirs();
        }

        File dbFile = new File(apkDbPath + "/" + dbName);
        b = dbFile.exists();
        if (!b) {
            File file = new File(innerPath);
            if (!file.exists()) {
                return false;
            }

            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(file);
                fos = new FileOutputStream(dbFile);
                fos.getChannel().transferFrom(fis.getChannel(), 0, fis.getChannel().size());

                Intent errorBackUp = new Intent(BackUpStatusReceiver.ACTION_BACK_UP_SUC);
                context.sendBroadcast(errorBackUp);

            } catch (FileNotFoundException e) {
                e.printStackTrace();

                Intent errorBackUp = new Intent(BackUpStatusReceiver.ACTION_BACK_UP_ERROR);
                context.sendBroadcast(errorBackUp);
            } catch (IOException e) {
                e.printStackTrace();

                Intent errorBackUp = new Intent(BackUpStatusReceiver.ACTION_BACK_UP_ERROR);
                context.sendBroadcast(errorBackUp);
            } finally {
                IOUtils.close(fos);
                IOUtils.close(fis);
            }
        }
        return !b;
    }
}
