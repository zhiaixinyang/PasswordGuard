package com.mdove.passwordguard.setting.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.deletelist.model.vm.DeleteDailySelfModelVM;
import com.mdove.passwordguard.main.model.BaseMainModel;

import java.util.List;

/**
 * Created by MDove on 2018/4/5.
 */

public interface SettingContract {
    interface Presenter extends BasePresenter<SettingContract.MvpView> {
        void initHideSysItem();

        void onClickBtnHideGroup();

        void onClickBtnHideSearch();

        void onClickBtnHideTimeTop();

        void onClickBtnHideOption();
    }

    interface MvpView extends BaseView<SettingContract.Presenter> {
        void showShowHideSysItem(List<BaseMainModel> data);

        void onClickBtnHideGroup(int position);

        void onClickBtnHideSearch(int position);

        void onClickBtnHideTimeTop(int position);

        void onClickBtnHideOption(int position);
    }
}
