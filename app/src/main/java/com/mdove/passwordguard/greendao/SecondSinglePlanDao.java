package com.mdove.passwordguard.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.mdove.passwordguard.greendao.entity.SecondSinglePlan;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SECOND_SINGLE_PLAN".
*/
public class SecondSinglePlanDao extends AbstractDao<SecondSinglePlan, Long> {

    public static final String TABLENAME = "SECOND_SINGLE_PLAN";

    /**
     * Properties of entity SecondSinglePlan.<br/>
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
        public final static Property MSinglePlanId = new Property(8, long.class, "mSinglePlanId", false, "M_SINGLE_PLAN_ID");
        public final static Property MLabel = new Property(9, String.class, "mLabel", false, "M_LABEL");
    }


    public SecondSinglePlanDao(DaoConfig config) {
        super(config);
    }
    
    public SecondSinglePlanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SECOND_SINGLE_PLAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"M_SINGLE_PLAN\" TEXT," + // 1: mSinglePlan
                "\"M_TIME\" INTEGER NOT NULL ," + // 2: mTime
                "\"M_IS_SUC\" INTEGER NOT NULL ," + // 3: mIsSuc
                "\"M_IS_SEE\" INTEGER NOT NULL ," + // 4: mIsSee
                "\"M_URGENT\" INTEGER NOT NULL ," + // 5: mUrgent
                "\"M_IMPORTANT\" INTEGER NOT NULL ," + // 6: mImportant
                "\"M_LABEL_ID\" INTEGER NOT NULL ," + // 7: mLabelId
                "\"M_SINGLE_PLAN_ID\" INTEGER NOT NULL ," + // 8: mSinglePlanId
                "\"M_LABEL\" TEXT);"); // 9: mLabel
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SECOND_SINGLE_PLAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SecondSinglePlan entity) {
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
        stmt.bindLong(9, entity.getMSinglePlanId());
 
        String mLabel = entity.getMLabel();
        if (mLabel != null) {
            stmt.bindString(10, mLabel);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SecondSinglePlan entity) {
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
        stmt.bindLong(9, entity.getMSinglePlanId());
 
        String mLabel = entity.getMLabel();
        if (mLabel != null) {
            stmt.bindString(10, mLabel);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public SecondSinglePlan readEntity(Cursor cursor, int offset) {
        SecondSinglePlan entity = new SecondSinglePlan( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // mSinglePlan
            cursor.getLong(offset + 2), // mTime
            cursor.getInt(offset + 3), // mIsSuc
            cursor.getInt(offset + 4), // mIsSee
            cursor.getInt(offset + 5), // mUrgent
            cursor.getInt(offset + 6), // mImportant
            cursor.getLong(offset + 7), // mLabelId
            cursor.getLong(offset + 8), // mSinglePlanId
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // mLabel
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SecondSinglePlan entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMSinglePlan(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMTime(cursor.getLong(offset + 2));
        entity.setMIsSuc(cursor.getInt(offset + 3));
        entity.setMIsSee(cursor.getInt(offset + 4));
        entity.setMUrgent(cursor.getInt(offset + 5));
        entity.setMImportant(cursor.getInt(offset + 6));
        entity.setMLabelId(cursor.getLong(offset + 7));
        entity.setMSinglePlanId(cursor.getLong(offset + 8));
        entity.setMLabel(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(SecondSinglePlan entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(SecondSinglePlan entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SecondSinglePlan entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
