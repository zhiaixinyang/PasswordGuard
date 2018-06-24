package com.mdove.passwordguard.singleplan.presenter;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.greendao.DeleteSelfTaskDao;
import com.mdove.passwordguard.greendao.SelfTaskDao;
import com.mdove.passwordguard.greendao.SelfTaskLabelDao;
import com.mdove.passwordguard.greendao.SelfTaskTimerDao;
import com.mdove.passwordguard.greendao.SinglePlanDao;
import com.mdove.passwordguard.greendao.SucSelfTaskDao;
import com.mdove.passwordguard.greendao.entity.SelfTask;
import com.mdove.passwordguard.greendao.entity.SelfTaskLabel;
import com.mdove.passwordguard.greendao.entity.SelfTaskTimer;
import com.mdove.passwordguard.greendao.entity.SinglePlan;
import com.mdove.passwordguard.greendao.entity.SucSelfTask;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModel;
import com.mdove.passwordguard.main.newmain.dailytask.model.BaseMainSelfTaskModel;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskModelVM;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskTimerModel;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskTimerModelVM;
import com.mdove.passwordguard.main.newmain.dailytask.presenter.contract.MainSelfTaskContract;
import com.mdove.passwordguard.main.newmain.dailytask.util.LabelTempModel;
import com.mdove.passwordguard.singleplan.model.SinglePlanTemp;
import com.mdove.passwordguard.singleplan.presenter.contract.EtSinglePlanContract;
import com.mdove.passwordguard.task.LabelSettingActivity;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.event.SelfTaskClickDeleteEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickEditEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickPriorityEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickSucEvent;
import com.mdove.passwordguard.task.utils.DeleteSelfTaskHelper;
import com.mdove.passwordguard.utils.ClipboardUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public class EtSinglePlanPresenter implements EtSinglePlanContract.Presenter {
    private EtSinglePlanContract.MvpView mView;
    private SelfTaskLabelDao mDao;
    private SinglePlanDao mSinglePlanDao;
    private List<DailyTaskLabelModel> mData;

    @Override
    public void subscribe(EtSinglePlanContract.MvpView view) {
        mView = view;
        mDao = App.getDaoSession().getSelfTaskLabelDao();
        mSinglePlanDao = App.getDaoSession().getSinglePlanDao();
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initLabel() {
        mData = new ArrayList<>();
        List<SelfTaskLabel> data = mDao.queryBuilder().list();
        for (SelfTaskLabel label : data) {
            mData.add(new DailyTaskLabelModel(label));
        }
        mView.initLabel(mData);
    }

    @Override
    public void onClickLabelSetting() {
        LabelSettingActivity.start(mView.getContext());
    }

    @Override
    public void addSinglePlan(SinglePlan temp) {
        mSinglePlanDao.insert(temp);
    }
}
