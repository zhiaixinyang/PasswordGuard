package com.mdove.passwordguard.main.presenter.contract;

import com.mdove.passwordguard.addoralter.model.AlterPasswordModel;
import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;
import com.mdove.passwordguard.deletelist.model.event.DeleteDailySelfReturnEvent;
import com.mdove.passwordguard.deletelist.model.event.DeletePasswordReturnEvent;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.event.CheckOrderEvent;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;

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

        void onClickBtnPassword();

        void onClickBtnLock();

        //Item被点击（PasswordModel）
        void onClickItemPassword(ItemMainPasswordVM model);

        void deleteDailySelf(ItemMainDailySelfVM vm);

        void onClickBtnDeletePassword();
        void onClickBtnDeleteDailySelf();

        void onClickBtnAddGroup();

        void onClickBtnSetting();

        void onClickBtnSearch();

        void querySearch(String queryKey);

        void deletePassword(int position, Password password);

        void deletePasswordReturn(DeletePasswordReturnEvent event);

        void deleteDailySelfReturn(DeleteDailySelfReturnEvent event);

        void checkUpdate(String version);

        void checkOrderPassword(CheckOrderEvent event);

        void insertDailySelf(String content);

        void favoriteDailySelf(ItemMainDailySelfVM vm);
        void copyDailySelf(ItemMainDailySelfVM vm);
        void copyPasswordInPassword(ItemMainPasswordVM vm);
        void copyPasswordInUserName(ItemMainPasswordVM vm);
    }

    interface MvpView extends BaseView<Presenter> {
        void showData(List<BaseMainModel> data);

        void addPasswordSuc(String suc);

        void notifyPasswordData(int position);
        void notifyDailySelfData(int position);

        void onClickBtnPassword(MainGroupModel model);

        void onClickBtnLock();

        void onClickBtnSearch();

        void searchReturn(List<Password> data, String error);

        void addGroupSuc();

        void deletePassword(int position);

        void deleteDailySelf(int position);

        void alterPasswordSuc(int itemPosition, int newItemPosition);

        void checkOrderSuc(List<BaseMainModel> data);
    }
}
