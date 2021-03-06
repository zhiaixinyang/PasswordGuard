package com.mdove.passwordguard.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.mdove.passwordguard.greendao.entity.SinglePlan;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SINGLE_PLAN".
*/
public class SinglePlanDao extends AbstractDao<SinglePlan, Long> {

    public static final String TABLENAME = "SINGLE_PLAN";

    /**
     * Properties of entity SinglePlan.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MSinglePlan = new Property(1, String.class, "mSinglePlan", false, "M_SINGLE_PLAN");
        public final static Property MTime = new Property(2, long.class, "mTime", false, "M_TIME");
        public final static Property MIsSuc = new Property(3, int.class, "mIsSuc", false, "M_IS_SUC");
        public final static Property MIsSee = new Property(4, int.class, "mIsSee", false, "M_IS_SEE");
        public final static Property MUrgent = new Property(5, int.class, "mUrgent", false, "M_URGENT");
        public final static Property MImportant = new Property(6, int.class, "mImportant", false, "M_IMPORTANT");
        public final static Property MLabelId = new Property(7, long.class, "mLabelId", false, "M_LABEL_ID");
        public final static Property MLabel = new Property(8, String.class, "mLabel", false, "M_LABEL");
    }


    public SinglePlanDao(DaoConfig config) {
        super(config);
    }
    
    public SinglePlanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SINGLE_PLAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"M_SINGLE_PLAN\" TEXT," + // 1: mSinglePlan
                "\"M_TIME\" INTEGER NOT NULL ," + // 2: mTime
                "\"M_IS_SUC\" INTEGER NOT NULL ," + // 3: mIsSuc
                "\"M_IS_SEE\" INTEGER NOT NULL ," + // 4: mIsSee
                "\"M_URGENT\" INTEGER NOT NULL ," + // 5: mUrgent
                "\"M_IMPORTANT\" INTEGER NOT NULL ," + // 6: mImportant
                "\"M_LABEL_ID\" INTEGER NOT NULL ," + // 7: mLabelId
                "\"M_LABEL\" TEXT);"); // 8: mLabel
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SINGLE_PLAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SinglePlan entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String mSinglePlan = entity.getMSinglePlan();
        if (mSinglePlan != null) {
            stmt.bindString(2, mSinglePlan);
        }
        stmt.bindLong(3, entity.getMTime());
        stmt.bindLong(4, entity.getMIsSuc());
        stmt.bindLong(5, entity.getMIsSee());
        stmt.bindLong(6, entity.getMUrgent());
        stmt.bindLong(7, entity.getMImportant());
        stmt.bindLong(8, entity.getMLabelId());
 
        String mLabel = entity.getMLabel();
        if (mLabel != null) {
            stmt.bindString(9, mLabel);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SinglePlan entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String mSinglePlan = entity.getMSinglePlan();
        if (mSinglePlan != null) {
            stmt.bindString(2, mSinglePlan);
        }
        stmt.bindLong(3, entity.getMTime());
        stmt.bindLong(4, entity.getMIsSuc());
        stmt.bindLong(5, entity.getMIsSee());
        stmt.bindLong(6, entity.getMUrgent());
        stmt.bindLong(7, entity.getMImportant());
        stmt.bindLong(8, entity.getMLabelId());
 
        String mLabel = entity.getMLabel();
        if (mLabel != null) {
            stmt.bindString(9, mLabel);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public SinglePlan readEntity(Cursor cursor, int offset) {
        SinglePlan entity = new SinglePlan( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // mSinglePlan
            cursor.getLong(offset + 2), // mTime
            cursor.getInt(offset + 3), // mIsSuc
            cursor.getInt(offset + 4), // mIsSee
            cursor.getInt(offset + 5), // mUrgent
            cursor.getInt(offset + 6), // mImportant
            cursor.getLong(offset + 7), // mLabelId
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // mLabel
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SinglePlan entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMSinglePlan(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMTime(cursor.getLong(offset + 2));
        entity.setMIsSuc(cursor.getInt(offset + 3));
        entity.setMIsSee(cursor.getInt(offset + 4));
        entity.setMUrgent(cursor.getInt(offset + 5));
        entity.setMImportant(cursor.getInt(offset + 6));
        entity.setMLabelId(cursor.getLong(offset + 7));
        entity.setMLabel(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(SinglePlan entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(SinglePlan entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SinglePlan entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
