package com.mdove.passwordguard.greendao.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mdove.passwordguard.greendao.AscribeTitleDao;
import com.mdove.passwordguard.greendao.CustomReViewDao;
import com.mdove.passwordguard.greendao.DailyPlanDao;
import com.mdove.passwordguard.greendao.DailySelfDao;
import com.mdove.passwordguard.greendao.InnerAscribeDao;
import com.mdove.passwordguard.greendao.LongPlanDao;
import com.mdove.passwordguard.greendao.MainTodayPlanDao;
import com.mdove.passwordguard.greendao.OuterAscribeDao;
import com.mdove.passwordguard.greendao.ScheduleDao;
import com.mdove.passwordguard.greendao.SecondTodayPlanDao;
import com.mdove.passwordguard.greendao.SelfTaskLabelDao;
import com.mdove.passwordguard.greendao.DaoMaster;
import com.mdove.passwordguard.greendao.DeleteSelfTaskDao;
import com.mdove.passwordguard.greendao.DeletedDailySelfDao;
import com.mdove.passwordguard.greendao.DeletedPasswordDao;
import com.mdove.passwordguard.greendao.GroupInfoDao;
import com.mdove.passwordguard.greendao.PasswordDao;
import com.mdove.passwordguard.greendao.SelfTaskDao;
import com.mdove.passwordguard.greendao.SelfTaskTimerDao;
import com.mdove.passwordguard.greendao.SinglePlanDao;
import com.mdove.passwordguard.greendao.SucSelfTaskDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by MDove on 2018/2/10.
 */

public class DBUpdateHelper extends DaoMaster.OpenHelper {
    public DBUpdateHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 数据库升级
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
                    @Override
                    public void onCreateAllTables(Database db, boolean ifNotExists) {
                        DaoMaster.createAllTables(db, ifNotExists);
                    }

                    @Override
                    public void onDropAllTables(Database db, boolean ifExists) {
                        DaoMaster.dropAllTables(db, ifExists);
                    }
                }, PasswordDao.class, DailySelfDao.class, GroupInfoDao.class, DeletedPasswordDao.class,
                SelfTaskDao.class, SinglePlanDao.class, DeleteSelfTaskDao.class, DeletedDailySelfDao.class, DailyPlanDao.class,
                SucSelfTaskDao.class, SelfTaskLabelDao.class, SelfTaskTimerDao.class, MainTodayPlanDao.class,
                SecondTodayPlanDao.class, InnerAscribeDao.class, OuterAscribeDao.class,
                AscribeTitleDao.class, ScheduleDao.class, CustomReViewDao.class, LongPlanDao.class);
    }
}
