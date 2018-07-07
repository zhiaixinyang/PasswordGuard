package com.mdove.passwordguard.home.richeditor.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.home.richeditor.model.RichEditorBtnModel;

import java.util.List;

/**
 * Created by MDove on 2018/7/3.
 */

public interface RichEditorContract {
    interface Presenter extends BasePresenter<RichEditorContract.MvpView> {
        void initRichEditorBtn();

        void onClickRichEditorBtn(int modelType);

        void onClickBack();

        void onClickReturn();
    }

    interface MvpView extends BaseView<Presenter> {
        void finish();

        void initRichEditorBtn(List<RichEditorBtnModel> data);

        void onClickReturn();

        void onClickRichEditorBtn(int modelType, int updatePosition);
    }
}
