package com.mdove.passwordguard.addoralter.presenter.contract;

import com.mdove.passwordguard.addoralter.model.AddPasswordGroupRlvModel;
import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.greendao.entity.GroupInfo;
import com.mdove.passwordguard.greendao.entity.Password;

import java.util.List;

/**
 * Created by MDove on 2018/2/19.
 */

public interface AddPasswordContract {
    interface Presenter extends BasePresenter<AddPasswordContract.MvpView> {
        void initGroup();
    }

    interface MvpView extends BaseView<Presenter> {
        void showGroup(List<AddPasswordGroupRlvModel> data);
    }
}
