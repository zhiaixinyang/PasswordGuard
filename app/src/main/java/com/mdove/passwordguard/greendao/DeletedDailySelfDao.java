package com.mdove.passwordguard.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.mdove.passwordguard.greendao.entity.DeletedDailySelf;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DELETED_DAILY_SELF".
*/
public class DeletedDailySelfDao extends AbstractDao<DeletedDailySelf, Long> {

    public static final String TABLENAME = "DELETED_DAILY_SELF";

    /**
     * Properties of entity DeletedDailySelf.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MContent = new Property(1, String.class, "mContent", false, "M_CONTENT");
        public final static Property MTimeStamp = new Property(2, Long.class, "mTimeStamp", false, "M_TIME_STAMP");
        public final static Property MDeletedTimeStamp = new Property(3, Long.class, "mDeletedTimeStamp", false, "M_DELETED_TIME_STAMP");
        public final static Property MTvGroup = new Property(4, String.class, "mTvGroup", false, "M_TV_GROUP");
    }


    public DeletedDailySelfDao(DaoConfig config) {
        super(config);
    }
    
    public DeletedDailySelfDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DELETED_DAILY_SELF\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"M_CONTENT\" TEXT," + // 1: mContent
                "\"M_TIME_STAMP\" INTEGER," + // 2: mTimeStamp
                "\"M_DELETED_TIME_STAMP\" INTEGER," + // 3: mDeletedTimeStamp
                "\"M_TV_GROUP\" TEXT);"); // 4: mTvGroup
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DELETED_DAILY_SELF\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DeletedDailySelf entity) {
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
 
        Long mDeletedTimeStamp = entity.getMDeletedTimeStamp();
        if (mDeletedTimeStamp != null) {
            stmt.bindLong(4, mDeletedTimeStamp);
        }
 
        String mTvGroup = entity.getMTvGroup();
        if (mTvGroup != null) {
            stmt.bindString(5, mTvGroup);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DeletedDailySelf entity) {
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
 
        Long mDeletedTimeStamp = entity.getMDeletedTimeStamp();
        if (mDeletedTimeStamp != null) {
            stmt.bindLong(4, mDeletedTimeStamp);
        }
 
        String mTvGroup = entity.getMTvGroup();
        if (mTvGroup != null) {
            stmt.bindString(5, mTvGroup);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DeletedDailySelf readEntity(Cursor cursor, int offset) {
        DeletedDailySelf entity = new DeletedDailySelf( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // mContent
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // mTimeStamp
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // mDeletedTimeStamp
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // mTvGroup
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DeletedDailySelf entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMContent(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMTimeStamp(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setMDeletedTimeStamp(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setMTvGroup(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DeletedDailySelf entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DeletedDailySelf entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DeletedDailySelf entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
