package com.mdove.passwordguard.main.model.handler;

import com.mdove.passwordguard.main.presenter.MainPresenter;

import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_ACCOUNT;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_COLLECT;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_GUIDE;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_LOCK;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_SELF_TASK;

/**
 * Created by MDove on 2018/3/21.
 */

public class ItemMainOptionHandler {
    private MainPresenter mPresenter;

    public ItemMainOptionHandler(MainPresenter presenter) {
        mPresenter = presenter;
    }

    public void onBtnClick(@MainPresenter.MainOpenInfoType int type) {
        switch (type) {
            case MAIN_OPEN_INFO_TYPE_ACCOUNT: {
                mPresenter.onClickBtnPassword();
                break;
            }
            case MAIN_OPEN_INFO_TYPE_LOCK: {
                mPresenter.onClickBtnLock();
                break;
            }
            case MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT: {
                mPresenter.onClickBtnDeletePassword();
                break;
            }
            case MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF: {
                mPresenter.onClickBtnDeleteDailySelf();
                break;
            }
            case MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF: {
                mPresenter.onClickBtnActivityDailySelf();
                break;
            }
            case MAIN_OPEN_INFO_TYPE_GUIDE: {
                mPresenter.onClickBtnGuide();
                break;
            }
            case MAIN_OPEN_INFO_TYPE_SELF_TASK: {
                mPresenter.onClickBtnSelfTask();
                break;
            }
            case MAIN_OPEN_INFO_TYPE_COLLECT: {
                mPresenter.onClickBtnSelfTask();
                break;
            }
            default: {
                break;
            }
        }
    }
}
