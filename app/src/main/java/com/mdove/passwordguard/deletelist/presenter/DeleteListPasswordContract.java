package com.mdove.passwordguard.deletelist.presenter;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.deletelist.model.vm.DeletePasswordModelVM;
import com.mdove.passwordguard.main.model.BaseMainModel;

import java.util.List;

/**
 * Created by MDove on 2018/2/14.
 */

public interface DeleteListPasswordContract {
    interface Presenter extends BasePresenter<DeleteListPasswordContract.MvpView> {
        void initData();

        void deleteReturn(DeletePasswordModelVM vm);

        void realDelete(DeletePasswordModelVM vm);

        void warningDeleteDialog(DeletePasswordModelVM vm);

    }

    interface MvpView extends BaseView<DeleteListPasswordContract.Presenter> {
        void showData(List<BaseMainModel> data);

        void deleteReturn(int position);

        void realDelete(int position);

        void warningDeleteDialog(DeletePasswordModelVM vm);

    }
}
