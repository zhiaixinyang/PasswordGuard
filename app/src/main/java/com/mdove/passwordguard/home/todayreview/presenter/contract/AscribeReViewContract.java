package com.mdove.passwordguard.home.todayreview.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.home.todayreview.model.CustomReViewModel;
import com.mdove.passwordguard.home.todayreview.model.InnerAscribeModel;
import com.mdove.passwordguard.home.todayreview.model.OuterAscribeModel;

import java.util.List;

/**
 * Created by MDove on 2018/7/12.
 */

public interface AscribeReViewContract {
    interface Presenter extends BasePresenter<AscribeReViewContract.MvpView> {
        void initData(List<InnerAscribeModel> innerAscribeModels, List<OuterAscribeModel> outerAscribeModels);

        void addInnerAscribe(String inner, String title);

        void addOuterAscribe(String outer, String title);
    }

    interface MvpView extends BaseView<Presenter> {
        void onAddInnerAscribe(int position);

        void onAddOuterAscribe(int position);
    }
}
