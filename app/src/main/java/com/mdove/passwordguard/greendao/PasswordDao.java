package com.mdove.passwordguard.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.mdove.passwordguard.greendao.entity.Password;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PASSWORD".
*/
public class PasswordDao extends AbstractDao<Password, Long> {

    public static final String TABLENAME = "PASSWORD";

    /**
     * Properties of entity Password.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MUserName = new Property(1, String.class, "mUserName", false, "M_USER_NAME");
        public final static Property MPassword = new Property(2, String.class, "mPassword", false, "M_PASSWORD");
        public final static Property MTitle = new Property(3, String.class, "mTitle", false, "M_TITLE");
        public final static Property MTimeStamp = new Property(4, Long.class, "mTimeStamp", false, "M_TIME_STAMP");
        public final static Property IsNew = new Property(5, int.class, "isNew", false, "IS_NEW");
        public final static Property MTvGroup = new Property(6, String.class, "mTvGroup", false, "M_TV_GROUP");
    }


    public PasswordDao(DaoConfig config) {
        super(config);
    }
    
    public PasswordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PASSWORD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"M_USER_NAME\" TEXT," + // 1: mUserName
                "\"M_PASSWORD\" TEXT," + // 2: mPassword
                "\"M_TITLE\" TEXT," + // 3: mTitle
                "\"M_TIME_STAMP\" INTEGER," + // 4: mTimeStamp
                "\"IS_NEW\" INTEGER NOT NULL ," + // 5: isNew
                "\"M_TV_GROUP\" TEXT);"); // 6: mTvGroup
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PASSWORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Password entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String mUserName = entity.getMUserName();
        if (mUserName != null) {
            stmt.bindString(2, mUserName);
        }
 
        String mPassword = entity.getMPassword();
        if (mPassword != null) {
            stmt.bindString(3, mPassword);
        }
 
        String mTitle = entity.getMTitle();
        if (mTitle != null) {
            stmt.bindString(4, mTitle);
        }
 
        Long mTimeStamp = entity.getMTimeStamp();
        if (mTimeStamp != null) {
            stmt.bindLong(5, mTimeStamp);
        }
        stmt.bindLong(6, entity.getIsNew());
 
        String mTvGroup = entity.getMTvGroup();
        if (mTvGroup != null) {
            stmt.bindString(7, mTvGroup);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Password entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String mUserName = entity.getMUserName();
        if (mUserName != null) {
            stmt.bindString(2, mUserName);
        }
 
        String mPassword = entity.getMPassword();
        if (mPassword != null) {
            stmt.bindString(3, mPassword);
        }
 
        String mTitle = entity.getMTitle();
        if (mTitle != null) {
            stmt.bindString(4, mTitle);
        }
 
        Long mTimeStamp = entity.getMTimeStamp();
        if (mTimeStamp != null) {
            stmt.bindLong(5, mTimeStamp);
        }
        stmt.bindLong(6, entity.getIsNew());
 
        String mTvGroup = entity.getMTvGroup();
        if (mTvGroup != null) {
            stmt.bindString(7, mTvGroup);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Password readEntity(Cursor cursor, int offset) {
        Password entity = new Password( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // mUserName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // mPassword
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // mTitle
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // mTimeStamp
            cursor.getInt(offset + 5), // isNew
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // mTvGroup
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Password entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMUserName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMPassword(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMTimeStamp(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setIsNew(cursor.getInt(offset + 5));
        entity.setMTvGroup(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Password entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Password entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Password entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
