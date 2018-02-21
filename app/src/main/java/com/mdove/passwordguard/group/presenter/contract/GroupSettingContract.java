package com.mdove.passwordguard.group.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.greendao.entity.GroupInfo;
import com.mdove.passwordguard.group.model.GroupSettingModel;

import java.util.List;

/**
 * Created by MDove on 2018/2/21.
 */

public interface GroupSettingContract {
    interface Presenter extends BasePresenter<GroupSettingContract.MvpView> {
        void initData();

        void deleteGroup(GroupInfo info, int position);
    }

    interface MvpView extends BaseView<Presenter> {
        void showData(List<GroupInfo> data);

        void deleteSuc(int position);
    }
}
