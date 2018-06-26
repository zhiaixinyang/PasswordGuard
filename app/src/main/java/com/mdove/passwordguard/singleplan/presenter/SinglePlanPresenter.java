package com.mdove.passwordguard.singleplan.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.SinglePlanDao;
import com.mdove.passwordguard.greendao.entity.SinglePlan;
import com.mdove.passwordguard.singleplan.EtSinglePlanActivity;
import com.mdove.passwordguard.singleplan.model.SinglePlanModel;
import com.mdove.passwordguard.singleplan.presenter.contract.SinglePlanContract;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public class SinglePlanPresenter implements SinglePlanContract.Presenter {
    private SinglePlanDao mSinglePlanDao;
    private SinglePlanContract.MvpView mView;
    private List<SinglePlanModel> mData;
    private List<SinglePlan> data;

    public SinglePlanPresenter() {
        mSinglePlanDao = App.getDaoSession().getSinglePlanDao();
    }

    @Override
    public void subscribe(SinglePlanContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initSinglePlan() {
        mData = new ArrayList<>();
        data = mSinglePlanDao.queryBuilder().list();
        for (SinglePlan singlePlan : data) {
            mData.add(new SinglePlanModel(singlePlan));
        }

        if (data == null || data.size() == 0) {
            mData.add(new SinglePlanModel("右上角进入添加计划页面。"));
        }

        mView.initSinglePlan(mData);
    }

    @Override
    public void onClickInEtSinglePlan() {
        EtSinglePlanActivity.start(mView.getContext());
    }

    @Override
    public void onClickDeleteSinglePlan(long id) {
        if (id == -1) {
            ToastHelper.shortToast("此条信息不可以删除。");
            return;
        }

        int position = -1;
        SinglePlanModel singlePlanModelTemp = null;
        for (SinglePlanModel singlePlanModel : mData) {
            if (singlePlanModel.mId == id) {
                singlePlanModelTemp = singlePlanModel;
            }
        }
        position = mData.indexOf(singlePlanModelTemp);

        if (position != -1) {
            SinglePlan s = mSinglePlanDao.queryBuilder().where(SinglePlanDao.Properties.Id.eq(id)).unique();
            if (s != null) {
                mSinglePlanDao.delete(s);
            }
            mView.deleteSinglePlan(position);
        }
    }
}
