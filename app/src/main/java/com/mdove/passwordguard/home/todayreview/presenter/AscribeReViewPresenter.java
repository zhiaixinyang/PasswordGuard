package com.mdove.passwordguard.home.todayreview.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.AscribeTitleDao;
import com.mdove.passwordguard.greendao.CustomReViewDao;
import com.mdove.passwordguard.greendao.InnerAscribeDao;
import com.mdove.passwordguard.greendao.OuterAscribeDao;
import com.mdove.passwordguard.greendao.entity.AscribeTitle;
import com.mdove.passwordguard.greendao.entity.InnerAscribe;
import com.mdove.passwordguard.greendao.entity.OuterAscribe;
import com.mdove.passwordguard.home.todayreview.model.CustomReViewModel;
import com.mdove.passwordguard.home.todayreview.model.InnerAscribeModel;
import com.mdove.passwordguard.home.todayreview.model.OuterAscribeModel;
import com.mdove.passwordguard.home.todayreview.presenter.contract.AscribeReViewContract;
import com.mdove.passwordguard.home.todayreview.presenter.contract.CustomReViewContract;

import java.util.Date;
import java.util.List;

public class AscribeReViewPresenter implements AscribeReViewContract.Presenter {
    private AscribeReViewContract.MvpView mView;
    private InnerAscribeDao mInnerAscribeDao;
    private OuterAscribeDao mOuterAscribeDao;
    private AscribeTitleDao mAscribeTitleDao;
    private boolean isInsert = false;
    private long mTitleId = -1;
    private List<InnerAscribeModel> innerAscribeModels;
    private List<OuterAscribeModel> outerAscribeModels;

    public AscribeReViewPresenter() {
        mOuterAscribeDao = App.getDaoSession().getOuterAscribeDao();
        mInnerAscribeDao = App.getDaoSession().getInnerAscribeDao();
        mAscribeTitleDao = App.getDaoSession().getAscribeTitleDao();
    }

    @Override
    public void subscribe(AscribeReViewContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initData(List<InnerAscribeModel> innerAscribeModels, List<OuterAscribeModel> outerAscribeModels) {
        this.innerAscribeModels = innerAscribeModels;
        this.outerAscribeModels = outerAscribeModels;
    }

    @Override
    public void addInnerAscribe(String inner, String title) {
        handleTitle(title);

        InnerAscribe innerAscribe = new InnerAscribe();
        innerAscribe.setMContent(inner);
        innerAscribe.setMTime(new Date().getTime());
        innerAscribe.setMBelongTitleId(mTitleId);

        mInnerAscribeDao.insert(innerAscribe);

        innerAscribeModels.add(new InnerAscribeModel(innerAscribe));
        mView.onAddInnerAscribe(innerAscribeModels.size());
    }

    @Override
    public void addOuterAscribe(String outer, String title) {
        handleTitle(title);

        OuterAscribe outerAscribe = new OuterAscribe();
        outerAscribe.setMContent(outer);
        outerAscribe.setMTime(new Date().getTime());
        outerAscribe.setMBelongTitleId(mTitleId);

        mOuterAscribeDao.insert(outerAscribe);

        outerAscribeModels.add(new OuterAscribeModel(outerAscribe));
        mView.onAddOuterAscribe(outerAscribeModels.size());
    }

    private void handleTitle(String title) {
        if (!isInsert) {
            isInsert = true;
            AscribeTitle ascribeTitle = new AscribeTitle();
            ascribeTitle.setMContent(title);
            ascribeTitle.setMTime(new Date().getTime());
            mTitleId = mAscribeTitleDao.insert(ascribeTitle);
        }
    }
}
