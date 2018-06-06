package com.mdove.passwordguard.main.newmain.timer.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskModelVM;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskTimerModelVM;
import com.mdove.passwordguard.main.newmain.dailytask.util.LabelTempModel;
import com.mdove.passwordguard.task.model.SelfTaskModel;

import java.util.List;

/**
 * Created by MDove on 2018/6/6.
 */

public interface MainSelfTaskTimerContract {
    interface Presenter extends BasePresenter<MainSelfTaskTimerContract.MvpView> {
        void insertSelfTaskTimer(String content);
    }

    interface MvpView extends BaseView<Presenter> {
        void insertSelfTaskTimer(String content);
    }
}
