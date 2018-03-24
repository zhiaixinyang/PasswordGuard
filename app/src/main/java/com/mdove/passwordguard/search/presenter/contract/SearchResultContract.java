package com.mdove.passwordguard.search.presenter.contract;

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
import com.mdove.passwordguard.main.model.event.CheckOrderEvent;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;

import java.util.List;

/**
 * Created by MDove on 2018/3/24.
 */

public interface SearchResultContract {
    interface Presenter extends BasePresenter<SearchResultContract.MvpView> {
        void initData(List<BaseMainModel> data);

        void favoriteDailySelf(ItemMainDailySelfVM vm);

        void copyDailySelf(ItemMainDailySelfVM vm);

        void copyPasswordInPassword(ItemMainPasswordVM vm);

        void copyPasswordInUserName(ItemMainPasswordVM vm);
    }

    interface MvpView extends BaseView<Presenter> {
        void notifyDailySelfData(int position);
    }
}
