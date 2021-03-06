package com.mdove.passwordguard.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.mdove.passwordguard.greendao.entity.DailyPlan;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DAILY_PLAN".
*/
public class DailyPlanDao extends AbstractDao<DailyPlan, Long> {

    public static final String TABLENAME = "DAILY_PLAN";

    /**
     * Properties of entity DailyPlan.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MContent = new Property(1, String.class, "mContent", false, "M_CONTENT");
        public final static Property MTimeStamp = new Property(2, Long.class, "mTimeStamp", false, "M_TIME_STAMP");
        public final static Property MStatus = new Property(3, Integer.class, "mStatus", false, "M_STATUS");
    }


    public DailyPlanDao(DaoConfig config) {
        super(config);
    }
    
    public DailyPlanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DAILY_PLAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"M_CONTENT\" TEXT," + // 1: mContent
                "\"M_TIME_STAMP\" INTEGER," + // 2: mTimeStamp
                "\"M_STATUS\" INTEGER);"); // 3: mStatus
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DAILY_PLAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DailyPlan entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String mContent = entity.getMContent();
        if (mContent != null) {
            stmt.bindString(2, mContent);
        }
 
        Long mTimeStamp = entity.getMTimeStamp();
        if (mTimeStamp != null) {
            stmt.bindLong(3, mTimeStamp);
        }
 
        Integer mStatus = entity.getMStatus();
        if (mStatus != null) {
            stmt.bindLong(4, mStatus);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DailyPlan entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String mContent = entity.getMContent();
        if (mContent != null) {
            stmt.bindString(2, mContent);
        }
 
        Long mTimeStamp = entity.getMTimeStamp();
        if (mTimeStamp != null) {
            stmt.bindLong(3, mTimeStamp);
        }
 
        Integer mStatus = entity.getMStatus();
        if (mStatus != null) {
            stmt.bindLong(4, mStatus);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DailyPlan readEntity(Cursor cursor, int offset) {
        DailyPlan entity = new DailyPlan( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // mContent
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // mTimeStamp
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3) // mStatus
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DailyPlan entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMContent(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMTimeStamp(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setMStatus(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DailyPlan entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DailyPlan entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DailyPlan entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
