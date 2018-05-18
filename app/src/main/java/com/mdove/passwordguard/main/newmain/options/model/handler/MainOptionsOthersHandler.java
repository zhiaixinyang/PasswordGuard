package com.mdove.passwordguard.main.newmain.options.model.handler;

import com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter;
import com.mdove.passwordguard.main.newmain.options.presenter.contract.OptionsContract;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.mainoption.presenter.AllMainOptionPresenter;

import static com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter.MAIN_OPEN_INFO_TYPE_ACCOUNT;
import static com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter.MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF;
import static com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter.MAIN_OPEN_INFO_TYPE_ALL_DAILY_SELF;
import static com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter.MAIN_OPEN_INFO_TYPE_ALL_PASSWORD;
import static com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter.MAIN_OPEN_INFO_TYPE_BACKUP;
import static com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter.MAIN_OPEN_INFO_TYPE_COLLECT;
import static com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter.MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT;
import static com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter.MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF;
import static com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter.MAIN_OPEN_INFO_TYPE_GUIDE;
import static com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter.MAIN_OPEN_INFO_TYPE_LOCK;
import static com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter.MAIN_OPEN_INFO_TYPE_SELF_TASK;
import static com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter.MAIN_OPEN_INFO_TYPE_SETTING;

/**
 * Created by MDove on 2018/5/18.
 */

public class MainOptionsOthersHandler {
    private OptionsPresenter mPresenter;

    public MainOptionsOthersHandler(OptionsPresenter presenter) {
        mPresenter = presenter;
    }

    public void onBtnClick(@OptionsPresenter.MainOptionsInfoType int type) {
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
                break;
            }
            case MAIN_OPEN_INFO_TYPE_SELF_TASK: {
                mPresenter.onClickBtnSelfTask();
                break;
            }
            case MAIN_OPEN_INFO_TYPE_COLLECT: {
                mPresenter.onClickBtnCollect();
                break;
            }
            case MAIN_OPEN_INFO_TYPE_ALL_PASSWORD: {
                mPresenter.onClickBtnAllPassword();
                break;
            }
            case MAIN_OPEN_INFO_TYPE_ALL_DAILY_SELF: {
                mPresenter.onClickBtnAllDailySelf();
                break;
            }
            case MAIN_OPEN_INFO_TYPE_SETTING: {
                mPresenter.onClickBtnSetting();
                break;
            }
            case MAIN_OPEN_INFO_TYPE_BACKUP: {
                mPresenter.onClickBtnBackUp();
                break;
            }
            default: {
                break;
            }
        }
    }
}
