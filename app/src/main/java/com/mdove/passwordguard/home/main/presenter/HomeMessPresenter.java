package com.mdove.passwordguard.home.main.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.CustomReViewDao;
import com.mdove.passwordguard.greendao.LongPlanDao;
import com.mdove.passwordguard.greendao.ScheduleDao;
import com.mdove.passwordguard.greendao.entity.CustomReView;
import com.mdove.passwordguard.greendao.entity.LongPlan;
import com.mdove.passwordguard.greendao.entity.Schedule;
import com.mdove.passwordguard.home.main.model.BaseHomeMessModel;
import com.mdove.passwordguard.home.main.model.HomeCustomReViewModel;
import com.mdove.passwordguard.home.main.model.HomeLongPlanModel;
import com.mdove.passwordguard.home.main.model.HomeScheduleModel;
import com.mdove.passwordguard.home.main.presenter.contract.HomeMessContract;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

public class HomeMessPresenter implements HomeMessContract.Presenter {
    public static final int DB_ITEM_TYPE_SCHEDULE = 1;
    public static final int DB_ITEM_TYPE_CUSTOM_REVIEW = 2;
    public static final int DB_ITEM_TYPE_LONG_PLAN = 3;
    private static final int ONE_PAGER_COUNT_NUM = 10;

    private int mCurDBType = 0;
    private int mCurDBTypeScheduleIndex = 0;
    private int mCurDBTypeCustomReviewIndex = 0;
    private int mCurDBTypeLongPlanIndex = 0;
    private HomeMessContract.MvpView mView;
    private List<BaseHomeMessModel> mData;
    private List<BaseHomeMessModel> mLaseData;
    private ScheduleDao mScheduleDao;
    private LongPlanDao mLongPlanDao;
    private CustomReViewDao mCustomReViewDao;

    public HomeMessPresenter() {
        mLaseData = new ArrayList<>();
        mScheduleDao = App.getDaoSession().getScheduleDao();
        mLongPlanDao = App.getDaoSession().getLongPlanDao();
        mCustomReViewDao = App.getDaoSession().getCustomReViewDao();
    }

    @Override
    public void initData() {
        mData = new ArrayList<>();

        List<Schedule> schedules = mScheduleDao.queryBuilder().limit(ONE_PAGER_COUNT_NUM).list();
        for (Schedule schedule : schedules) {
            mCurDBType = DB_ITEM_TYPE_SCHEDULE;
            if (mData.size() >= ONE_PAGER_COUNT_NUM) {
                break;
            }
            mData.add(new HomeScheduleModel(schedule));
        }
        mCurDBTypeScheduleIndex = mData.size() - 1;

        if (mCurDBTypeScheduleIndex < 9) {
            List<LongPlan> longPlans = mLongPlanDao.queryBuilder().
                    limit(ONE_PAGER_COUNT_NUM - mCurDBTypeScheduleIndex).list();
            for (LongPlan longPlan : longPlans) {
                mCurDBType = DB_ITEM_TYPE_LONG_PLAN;
                if (mData.size() >= ONE_PAGER_COUNT_NUM) {
                    break;
                }
                mData.add(new HomeLongPlanModel(longPlan));
            }
            mCurDBTypeLongPlanIndex = mData.size() - mCurDBTypeScheduleIndex - 1;
        }

        if (mCurDBTypeLongPlanIndex < 9) {
            List<CustomReView> customReViews = mCustomReViewDao.queryBuilder().
                    limit(ONE_PAGER_COUNT_NUM - mCurDBTypeLongPlanIndex).list();
            for (CustomReView customReView : customReViews) {
                mCurDBType = DB_ITEM_TYPE_CUSTOM_REVIEW;
                if (mData.size() >= ONE_PAGER_COUNT_NUM) {
                    break;
                }
                mData.add(new HomeCustomReViewModel(customReView));
            }
            mCurDBTypeCustomReviewIndex = mData.size() - mCurDBTypeLongPlanIndex - 1;
        }

        if (mLaseData.size() == mData.size()) {
            mView.onRefreshSuc(null);
            return;
        } else {
            mLaseData = mData;
            mView.initData(mData);
        }
    }

    @Override
    public void loadMore() {
        ToastHelper.shortToast("加载更多");
    }

    @Override
    public void subscribe(HomeMessContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }
}
