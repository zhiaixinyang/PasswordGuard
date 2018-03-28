package com.mdove.passwordguard.task.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;

import java.util.List;

/**
 * Created by MDove on 2018/3/27.
 */

public interface AllSelfTaskContract {
    interface Presenter extends BasePresenter<AllSelfTaskContract.MvpView> {
        void initData();

        void insertSelfTask(String content);

        void onClickTaskSuc(SelfTaskModelVM vm);

        void onClickSee(SelfTaskModelVM vm);

        void onClickDelete(SelfTaskModelVM vm);

        void onClickPriority(SelfTaskModelVM vm);

        void onClickCopy(SelfTaskModelVM vm);

        void onClickBtnEdit(SelfTaskModelVM vm,boolean isNoChange);
    }

    interface MvpView extends BaseView<Presenter> {
        void insertSelfTask(int position);

        void notifySelfTaskIsSuc(int position);

        void notifySelfTaskPriority(int position);

        void notifySelfSee(int position);

        void initData(List<SelfTaskModel> data);

        void onClickDelete(int position);

        void onClickBtnEdit(int position);
    }
}
