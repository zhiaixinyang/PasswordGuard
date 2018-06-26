package com.mdove.passwordguard.home.ettodayplan.presenter;

import com.mdove.passwordguard.home.ettodayplan.dialog.TimeBottomSheetDialog;
import com.mdove.passwordguard.home.ettodayplan.presenter.contract.EtTodayPlanContract;
import com.mdove.passwordguard.home.model.HomeTimeModelVM;
import com.mdove.passwordguard.utils.DateUtils;

import java.util.Date;

/**
 * Created by MDove on 2018/6/24.
 */

public class EtTodayPlanPresenter implements EtTodayPlanContract.Presenter {
    private EtTodayPlanContract.MvpView mView;

    @Override
    public void subscribe(EtTodayPlanContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void onClickBack() {
        mView.finish();
    }

    @Override
    public void onClickTime() {
        new TimeBottomSheetDialog(mView.getContext()).show();
    }
}
