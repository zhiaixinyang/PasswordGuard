package com.mdove.passwordguard.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.mdove.passwordguard.greendao.entity.AscribeTitle;
import com.mdove.passwordguard.greendao.entity.CustomReView;
import com.mdove.passwordguard.greendao.entity.DailyPlan;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.greendao.entity.DeletedDailySelf;
import com.mdove.passwordguard.greendao.entity.DeletedPassword;
import com.mdove.passwordguard.greendao.entity.DeleteSelfTask;
import com.mdove.passwordguard.greendao.entity.GroupInfo;
import com.mdove.passwordguard.greendao.entity.InnerAscribe;
import com.mdove.passwordguard.greendao.entity.LongPlan;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.OuterAscribe;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.greendao.entity.Schedule;
import com.mdove.passwordguard.greendao.entity.SecondSinglePlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.greendao.entity.SelfTask;
import com.mdove.passwordguard.greendao.entity.SelfTaskLabel;
import com.mdove.passwordguard.greendao.entity.SelfTaskTimer;
import com.mdove.passwordguard.greendao.entity.SinglePlan;
import com.mdove.passwordguard.greendao.entity.SucSelfTask;

import com.mdove.passwordguard.greendao.AscribeTitleDao;
import com.mdove.passwordguard.greendao.CustomReViewDao;
import com.mdove.passwordguard.greendao.DailyPlanDao;
import com.mdove.passwordguard.greendao.DailySelfDao;
import com.mdove.passwordguard.greendao.DeletedDailySelfDao;
import com.mdove.passwordguard.greendao.DeletedPasswordDao;
import com.mdove.passwordguard.greendao.DeleteSelfTaskDao;
import com.mdove.passwordguard.greendao.GroupInfoDao;
import com.mdove.passwordguard.greendao.InnerAscribeDao;
import com.mdove.passwordguard.greendao.LongPlanDao;
import com.mdove.passwordguard.greendao.MainTodayPlanDao;
import com.mdove.passwordguard.greendao.OuterAscribeDao;
import com.mdove.passwordguard.greendao.PasswordDao;
import com.mdove.passwordguard.greendao.ScheduleDao;
import com.mdove.passwordguard.greendao.SecondSinglePlanDao;
import com.mdove.passwordguard.greendao.SecondTodayPlanDao;
import com.mdove.passwordguard.greendao.SelfTaskDao;
import com.mdove.passwordguard.greendao.SelfTaskLabelDao;
import com.mdove.passwordguard.greendao.SelfTaskTimerDao;
import com.mdove.passwordguard.greendao.SinglePlanDao;
import com.mdove.passwordguard.greendao.SucSelfTaskDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig ascribeTitleDaoConfig;
    private final DaoConfig customReViewDaoConfig;
    private final DaoConfig dailyPlanDaoConfig;
    private final DaoConfig dailySelfDaoConfig;
    private final DaoConfig deletedDailySelfDaoConfig;
    private final DaoConfig deletedPasswordDaoConfig;
    private final DaoConfig deleteSelfTaskDaoConfig;
    private final DaoConfig groupInfoDaoConfig;
    private final DaoConfig innerAscribeDaoConfig;
    private final DaoConfig longPlanDaoConfig;
    private final DaoConfig mainTodayPlanDaoConfig;
    private final DaoConfig outerAscribeDaoConfig;
    private final DaoConfig passwordDaoConfig;
    private final DaoConfig scheduleDaoConfig;
    private final DaoConfig secondSinglePlanDaoConfig;
    private final DaoConfig secondTodayPlanDaoConfig;
    private final DaoConfig selfTaskDaoConfig;
    private final DaoConfig selfTaskLabelDaoConfig;
    private final DaoConfig selfTaskTimerDaoConfig;
    private final DaoConfig singlePlanDaoConfig;
    private final DaoConfig sucSelfTaskDaoConfig;

    private final AscribeTitleDao ascribeTitleDao;
    private final CustomReViewDao customReViewDao;
    private final DailyPlanDao dailyPlanDao;
    private final DailySelfDao dailySelfDao;
    private final DeletedDailySelfDao deletedDailySelfDao;
    private final DeletedPasswordDao deletedPasswordDao;
    private final DeleteSelfTaskDao deleteSelfTaskDao;
    private final GroupInfoDao groupInfoDao;
    private final InnerAscribeDao innerAscribeDao;
    private final LongPlanDao longPlanDao;
    private final MainTodayPlanDao mainTodayPlanDao;
    private final OuterAscribeDao outerAscribeDao;
    private final PasswordDao passwordDao;
    private final ScheduleDao scheduleDao;
    private final SecondSinglePlanDao secondSinglePlanDao;
    private final SecondTodayPlanDao secondTodayPlanDao;
    private final SelfTaskDao selfTaskDao;
    private final SelfTaskLabelDao selfTaskLabelDao;
    private final SelfTaskTimerDao selfTaskTimerDao;
    private final SinglePlanDao singlePlanDao;
    private final SucSelfTaskDao sucSelfTaskDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        ascribeTitleDaoConfig = daoConfigMap.get(AscribeTitleDao.class).clone();
        ascribeTitleDaoConfig.initIdentityScope(type);

        customReViewDaoConfig = daoConfigMap.get(CustomReViewDao.class).clone();
        customReViewDaoConfig.initIdentityScope(type);

        dailyPlanDaoConfig = daoConfigMap.get(DailyPlanDao.class).clone();
        dailyPlanDaoConfig.initIdentityScope(type);

        dailySelfDaoConfig = daoConfigMap.get(DailySelfDao.class).clone();
        dailySelfDaoConfig.initIdentityScope(type);

        deletedDailySelfDaoConfig = daoConfigMap.get(DeletedDailySelfDao.class).clone();
        deletedDailySelfDaoConfig.initIdentityScope(type);

        deletedPasswordDaoConfig = daoConfigMap.get(DeletedPasswordDao.class).clone();
        deletedPasswordDaoConfig.initIdentityScope(type);

        deleteSelfTaskDaoConfig = daoConfigMap.get(DeleteSelfTaskDao.class).clone();
        deleteSelfTaskDaoConfig.initIdentityScope(type);

        groupInfoDaoConfig = daoConfigMap.get(GroupInfoDao.class).clone();
        groupInfoDaoConfig.initIdentityScope(type);

        innerAscribeDaoConfig = daoConfigMap.get(InnerAscribeDao.class).clone();
        innerAscribeDaoConfig.initIdentityScope(type);

        longPlanDaoConfig = daoConfigMap.get(LongPlanDao.class).clone();
        longPlanDaoConfig.initIdentityScope(type);

        mainTodayPlanDaoConfig = daoConfigMap.get(MainTodayPlanDao.class).clone();
        mainTodayPlanDaoConfig.initIdentityScope(type);

        outerAscribeDaoConfig = daoConfigMap.get(OuterAscribeDao.class).clone();
        outerAscribeDaoConfig.initIdentityScope(type);

        passwordDaoConfig = daoConfigMap.get(PasswordDao.class).clone();
        passwordDaoConfig.initIdentityScope(type);

        scheduleDaoConfig = daoConfigMap.get(ScheduleDao.class).clone();
        scheduleDaoConfig.initIdentityScope(type);

        secondSinglePlanDaoConfig = daoConfigMap.get(SecondSinglePlanDao.class).clone();
        secondSinglePlanDaoConfig.initIdentityScope(type);

        secondTodayPlanDaoConfig = daoConfigMap.get(SecondTodayPlanDao.class).clone();
        secondTodayPlanDaoConfig.initIdentityScope(type);

        selfTaskDaoConfig = daoConfigMap.get(SelfTaskDao.class).clone();
        selfTaskDaoConfig.initIdentityScope(type);

        selfTaskLabelDaoConfig = daoConfigMap.get(SelfTaskLabelDao.class).clone();
        selfTaskLabelDaoConfig.initIdentityScope(type);

        selfTaskTimerDaoConfig = daoConfigMap.get(SelfTaskTimerDao.class).clone();
        selfTaskTimerDaoConfig.initIdentityScope(type);

        singlePlanDaoConfig = daoConfigMap.get(SinglePlanDao.class).clone();
        singlePlanDaoConfig.initIdentityScope(type);

        sucSelfTaskDaoConfig = daoConfigMap.get(SucSelfTaskDao.class).clone();
        sucSelfTaskDaoConfig.initIdentityScope(type);

        ascribeTitleDao = new AscribeTitleDao(ascribeTitleDaoConfig, this);
        customReViewDao = new CustomReViewDao(customReViewDaoConfig, this);
        dailyPlanDao = new DailyPlanDao(dailyPlanDaoConfig, this);
        dailySelfDao = new DailySelfDao(dailySelfDaoConfig, this);
        deletedDailySelfDao = new DeletedDailySelfDao(deletedDailySelfDaoConfig, this);
        deletedPasswordDao = new DeletedPasswordDao(deletedPasswordDaoConfig, this);
        deleteSelfTaskDao = new DeleteSelfTaskDao(deleteSelfTaskDaoConfig, this);
        groupInfoDao = new GroupInfoDao(groupInfoDaoConfig, this);
        innerAscribeDao = new InnerAscribeDao(innerAscribeDaoConfig, this);
        longPlanDao = new LongPlanDao(longPlanDaoConfig, this);
        mainTodayPlanDao = new MainTodayPlanDao(mainTodayPlanDaoConfig, this);
        outerAscribeDao = new OuterAscribeDao(outerAscribeDaoConfig, this);
        passwordDao = new PasswordDao(passwordDaoConfig, this);
        scheduleDao = new ScheduleDao(scheduleDaoConfig, this);
        secondSinglePlanDao = new SecondSinglePlanDao(secondSinglePlanDaoConfig, this);
        secondTodayPlanDao = new SecondTodayPlanDao(secondTodayPlanDaoConfig, this);
        selfTaskDao = new SelfTaskDao(selfTaskDaoConfig, this);
        selfTaskLabelDao = new SelfTaskLabelDao(selfTaskLabelDaoConfig, this);
        selfTaskTimerDao = new SelfTaskTimerDao(selfTaskTimerDaoConfig, this);
        singlePlanDao = new SinglePlanDao(singlePlanDaoConfig, this);
        sucSelfTaskDao = new SucSelfTaskDao(sucSelfTaskDaoConfig, this);

        registerDao(AscribeTitle.class, ascribeTitleDao);
        registerDao(CustomReView.class, customReViewDao);
        registerDao(DailyPlan.class, dailyPlanDao);
        registerDao(DailySelf.class, dailySelfDao);
        registerDao(DeletedDailySelf.class, deletedDailySelfDao);
        registerDao(DeletedPassword.class, deletedPasswordDao);
        registerDao(DeleteSelfTask.class, deleteSelfTaskDao);
        registerDao(GroupInfo.class, groupInfoDao);
        registerDao(InnerAscribe.class, innerAscribeDao);
        registerDao(LongPlan.class, longPlanDao);
        registerDao(MainTodayPlan.class, mainTodayPlanDao);
        registerDao(OuterAscribe.class, outerAscribeDao);
        registerDao(Password.class, passwordDao);
        registerDao(Schedule.class, scheduleDao);
        registerDao(SecondSinglePlan.class, secondSinglePlanDao);
        registerDao(SecondTodayPlan.class, secondTodayPlanDao);
        registerDao(SelfTask.class, selfTaskDao);
        registerDao(SelfTaskLabel.class, selfTaskLabelDao);
        registerDao(SelfTaskTimer.class, selfTaskTimerDao);
        registerDao(SinglePlan.class, singlePlanDao);
        registerDao(SucSelfTask.class, sucSelfTaskDao);
    }
    
    public void clear() {
        ascribeTitleDaoConfig.clearIdentityScope();
        customReViewDaoConfig.clearIdentityScope();
        dailyPlanDaoConfig.clearIdentityScope();
        dailySelfDaoConfig.clearIdentityScope();
        deletedDailySelfDaoConfig.clearIdentityScope();
        deletedPasswordDaoConfig.clearIdentityScope();
        deleteSelfTaskDaoConfig.clearIdentityScope();
        groupInfoDaoConfig.clearIdentityScope();
        innerAscribeDaoConfig.clearIdentityScope();
        longPlanDaoConfig.clearIdentityScope();
        mainTodayPlanDaoConfig.clearIdentityScope();
        outerAscribeDaoConfig.clearIdentityScope();
        passwordDaoConfig.clearIdentityScope();
        scheduleDaoConfig.clearIdentityScope();
        secondSinglePlanDaoConfig.clearIdentityScope();
        secondTodayPlanDaoConfig.clearIdentityScope();
        selfTaskDaoConfig.clearIdentityScope();
        selfTaskLabelDaoConfig.clearIdentityScope();
        selfTaskTimerDaoConfig.clearIdentityScope();
        singlePlanDaoConfig.clearIdentityScope();
        sucSelfTaskDaoConfig.clearIdentityScope();
    }

    public AscribeTitleDao getAscribeTitleDao() {
        return ascribeTitleDao;
    }

    public CustomReViewDao getCustomReViewDao() {
        return customReViewDao;
    }

    public DailyPlanDao getDailyPlanDao() {
        return dailyPlanDao;
    }

    public DailySelfDao getDailySelfDao() {
        return dailySelfDao;
    }

    public DeletedDailySelfDao getDeletedDailySelfDao() {
        return deletedDailySelfDao;
    }

    public DeletedPasswordDao getDeletedPasswordDao() {
        return deletedPasswordDao;
    }

    public DeleteSelfTaskDao getDeleteSelfTaskDao() {
        return deleteSelfTaskDao;
    }

    public GroupInfoDao getGroupInfoDao() {
        return groupInfoDao;
    }

    public InnerAscribeDao getInnerAscribeDao() {
        return innerAscribeDao;
    }

    public LongPlanDao getLongPlanDao() {
        return longPlanDao;
    }

    public MainTodayPlanDao getMainTodayPlanDao() {
        return mainTodayPlanDao;
    }

    public OuterAscribeDao getOuterAscribeDao() {
        return outerAscribeDao;
    }

    public PasswordDao getPasswordDao() {
        return passwordDao;
    }

    public ScheduleDao getScheduleDao() {
        return scheduleDao;
    }

    public SecondSinglePlanDao getSecondSinglePlanDao() {
        return secondSinglePlanDao;
    }

    public SecondTodayPlanDao getSecondTodayPlanDao() {
        return secondTodayPlanDao;
    }

    public SelfTaskDao getSelfTaskDao() {
        return selfTaskDao;
    }

    public SelfTaskLabelDao getSelfTaskLabelDao() {
        return selfTaskLabelDao;
    }

    public SelfTaskTimerDao getSelfTaskTimerDao() {
        return selfTaskTimerDao;
    }

    public SinglePlanDao getSinglePlanDao() {
        return singlePlanDao;
    }

    public SucSelfTaskDao getSucSelfTaskDao() {
        return sucSelfTaskDao;
    }

}
