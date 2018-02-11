package com.mdove.passwordguard.main.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.main.model.BaseMainModel;

import java.util.List;

/**
 * Created by MDove on 2018/2/10.
 */

public interface MainContract {
    interface Presenter extends BasePresenter<MainContract.MvpView> {
        void initData();
        void addPassword(Password password);

        void onClickBtnPassword();
        void onClickBtnLock();
    }

    interface MvpView extends BaseView<Presenter> {
        void showData(List<BaseMainModel> data);
        void addPasswordSuc(String suc);
        void notifyPasswordData(int position);

        void onClickBtnPassword();
        void onClickBtnLock();
    }
}
