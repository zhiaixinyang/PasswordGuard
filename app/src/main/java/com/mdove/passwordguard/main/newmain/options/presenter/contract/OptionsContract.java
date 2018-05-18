package com.mdove.passwordguard.main.newmain.options.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.main.newmain.options.model.MainOptionNewInfo;
import com.mdove.passwordguard.main.newmain.options.model.MainOptionsTopModel;

import java.util.List;

/**
 * Created by MDove on 2018/5/18.
 */

public interface OptionsContract {
    interface Presenter extends BasePresenter<OptionsContract.MvpView> {
        void initData();

        void onClickBtnPassword();

        void onClickBtnLock();

        void onClickBtnDeletePassword();

        void onClickBtnDeleteDailySelf();

        void onClickBtnSetting();

        void onClickBtnAllPassword();

        void onClickBtnAllDailySelf();

        void onClickBtnActivityDailySelf();

        void onClickBtnSelfTask();

        void onClickBtnCollect();

        void onClickBtnBackUp();
    }

    interface MvpView extends BaseView<Presenter> {
        void showTopOptions(MainOptionsTopModel model);
        void showOtherOptions(List<MainOptionNewInfo> data);
    }
}
