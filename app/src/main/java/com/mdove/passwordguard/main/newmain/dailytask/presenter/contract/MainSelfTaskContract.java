package com.mdove.passwordguard.main.newmain.dailytask.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskModelVM;
import com.mdove.passwordguard.task.model.SelfTaskModel;

import java.util.List;

/**
 * Created by MDove on 2018/5/9.
 */

public interface MainSelfTaskContract {
    interface Presenter extends BasePresenter<MainSelfTaskContract.MvpView> {
        void initData();

        void insertSelfTask(String content);

        void onClickTaskSuc(MainSelfTaskModelVM vm);

        void onClickSee(MainSelfTaskModelVM vm);

        void onClickDelete(MainSelfTaskModelVM vm);

        void onClickPriority(MainSelfTaskModelVM vm);

        void onClickCopy(MainSelfTaskModelVM vm);

        void onClickBtnEdit(MainSelfTaskModelVM vm, boolean isNoChange);
    }

    interface MvpView extends BaseView<Presenter> {
        void insertSelfTask(int position);

        void initData(List<SelfTaskModel> data);

        void notifySelfTaskIsSuc(int position);

        void notifySelfTaskPriority(int position);

        void notifySelfSee(int position);

        void onClickDelete(int position);

        void onClickBtnEdit(int position);
    }
}
