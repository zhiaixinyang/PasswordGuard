package com.mdove.passwordguard.task.presenter;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.SelfTaskLabelDao;
import com.mdove.passwordguard.greendao.entity.SelfTaskLabel;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModel;
import com.mdove.passwordguard.task.model.event.SelfTaskLabelInsertEvent;
import com.mdove.passwordguard.task.presenter.contract.LabelSettingContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/5/28.
 */

public class LabelSettingPresenter implements LabelSettingContract.Presenter {
    private LabelSettingContract.MvpView mView;
    private List<DailyTaskLabelModel> mData;
    private SelfTaskLabelDao mDao;

    public LabelSettingPresenter() {
        mDao = App.getDaoSession().getSelfTaskLabelDao();
    }

    @Override
    public void subscribe(LabelSettingContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initData() {
        mData = new ArrayList<>();
        List<SelfTaskLabel> data = mDao.queryBuilder().list();
        for (SelfTaskLabel label : data) {
            mData.add(new DailyTaskLabelModel(label));
        }
        mView.initData(mData);
    }

    @Override
    public void deleteLabel(long id) {
        int position = -1;
        for (DailyTaskLabelModel model : mData) {
            if (model.mLabel.getId() == id) {
                position = mData.indexOf(model);
                break;
            }
        }
        if (position != -1) {
            mView.deleteLabel(position);
        }
    }

    @Override
    public void insertLabel(String content) {
        SelfTaskLabel label = new SelfTaskLabel();
        label.setMTvLabel(content);
        mDao.insert(label);

        DailyTaskLabelModel model = new DailyTaskLabelModel(label);
        mData.add(model);
        mView.insertLabel(mData.size());
        RxBus.get().post(new SelfTaskLabelInsertEvent(model));
    }
}
