package com.mdove.passwordguard.task.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModel;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;

import java.util.List;

/**
 * Created by MDove on 2018/5/28.
 */

public interface LabelSettingContract {
    interface Presenter extends BasePresenter<LabelSettingContract.MvpView> {
        void initData();

        void deleteLabel(long id);

        void insertLabel(String content);
    }

    interface MvpView extends BaseView<Presenter> {
        void initData(List<DailyTaskLabelModel> data);

        void deleteLabel(int position);

        void insertLabel(int position);
    }
}
