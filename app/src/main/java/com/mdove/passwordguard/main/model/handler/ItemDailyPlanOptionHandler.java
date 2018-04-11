package com.mdove.passwordguard.main.model.handler;

import com.mdove.passwordguard.main.model.DailyPlanOptionInfo;
import com.mdove.passwordguard.main.presenter.DailyPlanOptionPresenter;
import com.mdove.passwordguard.main.presenter.MainPresenter;

import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_ACCOUNT;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_COLLECT;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_GUIDE;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_LOCK;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_MAIN_ALL_OPTION;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_SELF_TASK;

/**
 * Created by MDove on 2018/4/11.
 */

public class ItemDailyPlanOptionHandler {
    private DailyPlanOptionPresenter mPresenter;

    public ItemDailyPlanOptionHandler(DailyPlanOptionPresenter presenter) {
        mPresenter = presenter;
    }

    public void onBtnClick(@DailyPlanOptionPresenter.DailyOpenInfoType int type) {
        if (mPresenter == null) {
            return;
        }
        switch (type) {
            case DailyPlanOptionPresenter.DAILY_PLAN_OPTION_TYPE_CALENDER:{

                break;
            }
            default: {
                break;
            }
        }
    }
}
