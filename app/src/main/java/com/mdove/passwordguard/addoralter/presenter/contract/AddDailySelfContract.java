package com.mdove.passwordguard.addoralter.presenter.contract;

import com.mdove.passwordguard.addoralter.model.AddDailySelfGroupRlvModel;
import com.mdove.passwordguard.addoralter.model.AlterDailySelfModel;
import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.greendao.entity.DailySelf;

import java.util.List;

/**
 * Created by MDove on 2018/3/21.
 */

public interface AddDailySelfContract {
    interface Presenter extends BasePresenter<AddDailySelfContract.MvpView> {
        void initGroup();

        void insertDailySelf(DailySelf dailySelf);

        void alterDailySelf(AlterDailySelfModel model, int itemPosition);
    }

    interface MvpView extends BaseView<Presenter> {
        void showGroup(List<AddDailySelfGroupRlvModel> data);
    }
}
