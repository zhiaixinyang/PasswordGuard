package com.mdove.passwordguard.main.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.greendao.PasswordDao;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.main.model.PasswordModel;
import com.mdove.passwordguard.main.presenter.contract.MainContract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/2/10.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.MvpView mView;
    private List<BaseMainModel> mData;
    private PasswordDao mDao;

    @Override
    public void subscribe(MainContract.MvpView view) {
        mView = view;
        mData = new ArrayList<>();
        mDao = App.getDaoSession().getPasswordDao();
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initData() {
        initSys();

        List<Password> data = mDao.queryBuilder().build().list();
        for (Password password : data) {
            mData.add(new PasswordModel(password));
        }

        mView.showData(mData);
    }

    private void initSys() {
        MainTopModel mainTopModel = new MainTopModel();
        mainTopModel.mTime = new Date();
        mainTopModel.mType = 1;
        mData.add(mainTopModel);

        BaseMainModel optionModel = new BaseMainModel();
        optionModel.mType = 0;
        mData.add(optionModel);
    }

    @Override
    public void addPassword(Password password) {
        long id = mDao.insert(password);
        if (id != -1) {
            PasswordModel model = new PasswordModel(password);
            mData.add(model);

            mView.notifyPasswordData(mData.size());
            mView.addPasswordSuc(mView.getContext().getString(R.string.string_add_password_suc));
        }
    }

    @Override
    public void onClickBtnPassword() {
        mView.onClickBtnPassword();
    }
}
