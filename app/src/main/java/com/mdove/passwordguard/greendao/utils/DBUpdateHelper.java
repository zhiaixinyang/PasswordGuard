package com.mdove.passwordguard.greendao.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mdove.passwordguard.greendao.DailySelfDao;
import com.mdove.passwordguard.greendao.DaoMaster;
import com.mdove.passwordguard.greendao.DeletedPasswordDao;
import com.mdove.passwordguard.greendao.GroupInfoDao;
import com.mdove.passwordguard.greendao.PasswordDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by MDove on 2018/2/10.
 */

public class DBUpdateHelper extends DaoMaster.DevOpenHelper {
    public DBUpdateHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //操作数据库的更新 有几个表升级都可以传入到下面
        MigrationHelper.getInstance().migrate(db,PasswordDao.class);
        MigrationHelper.getInstance().migrate(db,DailySelfDao.class);
        MigrationHelper.getInstance().migrate(db,GroupInfoDao.class);
        MigrationHelper.getInstance().migrate(db,DeletedPasswordDao.class);
    }

}
