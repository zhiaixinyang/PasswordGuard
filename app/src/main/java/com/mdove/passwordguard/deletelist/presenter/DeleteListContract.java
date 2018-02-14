package com.mdove.passwordguard.deletelist.presenter;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.presenter.contract.MainContract;

import java.util.List;

/**
 * Created by MDove on 2018/2/14.
 */

public interface DeleteListContract {
    interface Presenter extends BasePresenter<DeleteListContract.MvpView> {
        void initData();
    }

    interface MvpView extends BaseView<DeleteListContract.Presenter> {
        void showData(List<BaseMainModel> data);
    }
}
