package com.mdove.passwordguard.deletelist.presenter;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.deletelist.model.vm.DeleteDailySelfModelVM;
import com.mdove.passwordguard.deletelist.model.vm.DeletePasswordModelVM;
import com.mdove.passwordguard.main.model.BaseMainModel;

import java.util.List;

/**
 * Created by MDove on 2018/2/14.
 */

public interface DeleteListDailySelfContract {
    interface Presenter extends BasePresenter<DeleteListDailySelfContract.MvpView> {
        void initData();
        void deleteReturn(DeleteDailySelfModelVM vm);
        void realDelete(DeleteDailySelfModelVM vm);
    }

    interface MvpView extends BaseView<DeleteListDailySelfContract.Presenter> {
        void showData(List<BaseMainModel> data);
        void deleteReturn(int position);
        void realDelete(int position);
    }
}
