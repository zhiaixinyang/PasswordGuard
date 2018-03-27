package com.mdove.passwordguard.task.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.task.model.DeleteSelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;

import java.util.List;

/**
 * Created by MDove on 2018/3/27.
 */

public interface DeleteSelfTaskContract {
    interface Presenter extends BasePresenter<DeleteSelfTaskContract.MvpView> {
        void initData();

        void onClickDelete(long id);
    }

    interface MvpView extends BaseView<Presenter> {
        void initData(List<DeleteSelfTaskModel> data);

        void onClickDelete(int position);

    }
}
