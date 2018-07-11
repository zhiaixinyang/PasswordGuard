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
        mCurDBTypeScheduleIndex = 0;
        mCurDBTypeCustomReviewIndex = 0;
        mCurDBTypeLongPlanIndex = 0;

        List<Schedule> schedules = mScheduleDao.queryBuilder().limit(ONE_PAGER_COUNT_NUM).list();
        for (Schedule schedule : schedules) {
            mCurDBType = DB_ITEM_TYPE_SCHEDULE;
            if (mData.size() >= ONE_PAGER_COUNT_NUM) {
                break;
            }
            mData.add(new HomeScheduleModel(schedule));
        }
        mCurDBTypeScheduleIndex = mData.size();

        if (mData.size() < ONE_PAGER_COUNT_NUM) {
            int curScheduleEndIndex = mData.size();
            List<LongPlan> longPlans = mLongPlanDao.queryBuilder().
                    limit(ONE_PAGER_COUNT_NUM).list();
            for (LongPlan longPlan : longPlans) {
                mCurDBType = DB_ITEM_TYPE_LONG_PLAN;
                if (mData.size() >= ONE_PAGER_COUNT_NUM) {
                    break;
                }
                mData.add(new HomeLongPlanModel(longPlan));
            }
            mCurDBTypeLongPlanIndex = mData.size() - curScheduleEndIndex;
        }

        if (mData.size() < ONE_PAGER_COUNT_NUM) {
            int curLongPlanEndIndex = mData.size();
            List<CustomReView> customReViews = mCustomReViewDao.queryBuilder().
                    limit(ONE_PAGER_COUNT_NUM).list();
            for (CustomReView customReView : customReViews) {
                mCurDBType = DB_ITEM_TYPE_CUSTOM_REVIEW;
                if (mData.size() >= ONE_PAGER_COUNT_NUM) {
                    break;
                }
                mData.add(new HomeCustomReViewModel(customReView));
            }
            mCurDBTypeCustomReviewIndex = mData.size() - curLongPlanEndIndex;
        }

        if (mLaseData.size() == mData.size()) {
            mView.onRefreshSuc(null);
            return;
        } else {
            mLaseData = mData;
            mView.onRefreshSuc(mData);
        }
    }

    @Override
    public void loadMore() {
        List<BaseHomeMessModel> data = new ArrayList<>();
        switch (mCurDBType) {
            case DB_ITEM_TYPE_SCHEDULE: {
                loadMoreSchedule(data);
                break;
            }
            case DB_ITEM_TYPE_CUSTOM_REVIEW: {
                loadMoreCustomReView(data);
                break;
            }
            case DB_ITEM_TYPE_LONG_PLAN: {
                loadMoreLongPlan(data);
                break;
            }
            default: {
                break;
            }
        }
        mView.onLoadMore(data);
    }

    private void loadMoreSchedule(List<BaseHomeMessModel> data) {
        List<Schedule> schedules = mScheduleDao.queryBuilder()
                .limit(ONE_PAGER_COUNT_NUM).offset(mCurDBTypeScheduleIndex).list();
        for (Schedule schedule : schedules) {
            mCurDBType = DB_ITEM_TYPE_SCHEDULE;
            if (data.size() >= ONE_PAGER_COUNT_NUM) {
                break;
            }
            data.add(new HomeScheduleModel(schedule));
        }
        mCurDBTypeScheduleIndex += data.size();

        if (data.size() < ONE_PAGER_COUNT_NUM) {
            loadMoreLongPlan(data);
        }
    }

    private void loadMoreLongPlan(List<BaseHomeMessModel> data) {
        int curIndex = data.size();

        List<LongPlan> longPlans = mLongPlanDao.queryBuilder().
                limit(ONE_PAGER_COUNT_NUM).offset(mCurDBTypeLongPlanIndex).list();
        for (LongPlan longPlan : longPlans) {
            mCurDBType = DB_ITEM_TYPE_LONG_PLAN;
            if (data.size() >= ONE_PAGER_COUNT_NUM) {
                break;
            }
            data.add(new HomeLongPlanModel(longPlan));
        }
        mCurDBTypeLongPlanIndex += data.size() - curIndex;

        if (data.size() < ONE_PAGER_COUNT_NUM) {
            loadMoreCustomReView(data);
        }
    }

    private void loadMoreCustomReView(List<BaseHomeMessModel> data) {
        int curIndex = data.size();

        List<CustomReView> customReViews = mCustomReViewDao.queryBuilder()
                .limit(ONE_PAGER_COUNT_NUM).offset(mCurDBTypeCustomReviewIndex).list();
        for (CustomReView customReView : customReViews) {
            mCurDBType = DB_ITEM_TYPE_CUSTOM_REVIEW;
            if (data.size() >= ONE_PAGER_COUNT_NUM) {
                break;
            }
            data.add(new HomeCustomReViewModel(customReView));
        }
        mCurDBTypeCustomReviewIndex += data.size() - curIndex;
    }

    @Override
    public void subscribe(HomeMessContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }
}
