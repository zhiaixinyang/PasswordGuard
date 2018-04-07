package com.mdove.passwordguard.mainoption.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.main.model.MainOptionInfo;

import java.util.List;

/**
 * Created by MDove on 2018/4/2.
 */

public interface AllMainOptionContract {
    interface Presenter extends BasePresenter<AllMainOptionContract.MvpView> {
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
    }

    interface MvpView extends BaseView<Presenter> {
        void showData(List<MainOptionInfo> data);
    }
}
