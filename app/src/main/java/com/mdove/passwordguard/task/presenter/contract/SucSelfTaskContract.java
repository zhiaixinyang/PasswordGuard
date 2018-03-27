package com.mdove.passwordguard.task.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.task.model.DeleteSelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SucSelfTaskModel;

import java.util.List;

/**
 * Created by MDove on 2018/3/27.
 */

public interface SucSelfTaskContract {
    interface Presenter extends BasePresenter<SucSelfTaskContract.MvpView> {
        void initData();
    }

    interface MvpView extends BaseView<Presenter> {
        void initData(List<SucSelfTaskModel> data);
    }
}
