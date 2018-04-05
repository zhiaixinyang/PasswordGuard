package com.mdove.passwordguard.mainoption.presenter.contract;

import com.mdove.passwordguard.addoralter.model.AlterDailySelfModel;
import com.mdove.passwordguard.addoralter.model.AlterPasswordModel;
import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;
import com.mdove.passwordguard.deletelist.model.event.DeleteDailySelfReturnEvent;
import com.mdove.passwordguard.deletelist.model.event.DeletePasswordReturnEvent;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.MainOptionInfo;
import com.mdove.passwordguard.main.model.event.CheckOrderEvent;
import com.mdove.passwordguard.main.model.impl.IHideVm;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;

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
