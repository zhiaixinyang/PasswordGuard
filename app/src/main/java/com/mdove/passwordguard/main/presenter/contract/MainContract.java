package com.mdove.passwordguard.main.presenter.contract;

import com.mdove.passwordguard.addoralter.model.AlterDailySelfModel;
import com.mdove.passwordguard.addoralter.model.AlterPasswordModel;
import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;
import com.mdove.passwordguard.deletelist.model.event.DeleteDailySelfReturnEvent;
import com.mdove.passwordguard.deletelist.model.event.DeletePasswordReturnEvent;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.main.adapter.MainSelfTaskAdapter;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.event.CheckOrderEvent;
import com.mdove.passwordguard.main.model.impl.IHideVm;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;

import java.util.List;

/**
 * Created by MDove on 2018/2/10.
 */

public interface MainContract {
    interface Presenter extends BasePresenter<MainContract.MvpView> {
        void initData();

        void addPassword(Password password);

        void addGroup(String tvGroup);

        void alterPassword(AlterPasswordModel model, int itemPosition);

        void alterDailySelf(AlterDailySelfModel model, int itemPosition);

        void onClickBtnPassword();

        void onClickBtnLock();

        //Item被点击（MainPasswordModel）
        void onClickItemPassword(ItemMainPasswordVM model);

        void onClickItemDailySelf(ItemMainDailySelfVM model);

        void deleteDailySelf(ItemMainDailySelfVM vm);

        void onClickBtnDeletePassword();

        void onClickBtnDeleteDailySelf();

        void onClickBtnAddGroup();

        void onClickBtnHideGroup();

        void onClickBtnHideSearch();

        void onClickBtnHideOption();

        void onClickBtnHideTimeTop();

        void onClickBtnSetting();

        void onClickBtnActivityDailySelf();

        void onClickBtnGuide();

        void onClickBtnSelfTask();

        void onClickBtnCollect();

        void onClickBtnAllMainOption();

        void onClickBtnSearch();

        void onClickBtnAddDailySelf();

        void querySearch(String queryKey);

        void deletePassword(int position, Password password);

        void deletePasswordReturn(DeletePasswordReturnEvent event);

        void deleteDailySelfReturn(DeleteDailySelfReturnEvent event);

        void checkUpdate(String version);

        void checkOrderPassword(CheckOrderEvent event);

        void insertDailySelf(String content);

        void addDailySelf(DailySelf dailySelf);

        void favoriteDailySelf(ItemMainDailySelfVM vm);

        void btnHidePworDs(IHideVm vm);

        void favoritePassword(ItemMainPasswordVM vm);

        void copyDailySelf(ItemMainDailySelfVM vm);

        void copyPasswordInPassword(ItemMainPasswordVM vm);

        void copyPasswordInUserName(ItemMainPasswordVM vm);

        void onClickTaskSuc(SelfTaskModelVM vm);
        void onClickSee(SelfTaskModelVM vm);
        void onClickPriority(SelfTaskModelVM vm);
        void onClickCopyTaskSelf(SelfTaskModelVM vm);
    }

    interface MvpView extends BaseView<Presenter> {
        void showData(List<BaseMainModel> data);

        void addPasswordSuc(String suc);

        void notifyPasswordData(int position);

        void notifyDailySelfData(int position);

        void notifyBtnHide(int position);

        void onClickBtnPassword(MainGroupModel model);

        void onClickBtnLock();

        void onShowGuide();

        void onClickBtnSearch();

        void onClickBtnAddDailySelf();

        void searchReturn(List<BaseMainModel> data, String error);

        void addGroupSuc();

        void deletePassword(int position);

        void deleteDailySelf(int position);

        void alterPasswordSuc(int itemPosition, int newItemPosition);

        void alterDailySelfSuc(int itemPosition);

        void checkOrderSuc(List<BaseMainModel> data);

        void onClickTaskSuc(int position);

        void onClickBtnHideGroup(int position);

        void onClickBtnHideSearch(int position);

        void onClickBtnHideOption(int position);

        void onClickBtnHideTimeTop(int position);

        void notifyTaskSelf(int position);
        void notifyTaskSelfSee(int position);

    }
}
