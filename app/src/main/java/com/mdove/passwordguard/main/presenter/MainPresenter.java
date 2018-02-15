package com.mdove.passwordguard.main.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.addoralter.model.AlterPasswordModel;
import com.mdove.passwordguard.greendao.DeletedPasswordDao;
import com.mdove.passwordguard.greendao.PasswordDao;
import com.mdove.passwordguard.greendao.entity.DeletedPassword;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.greendao.utils.DeletedPasswordHelper;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainSearchModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.main.model.PasswordModel;
import com.mdove.passwordguard.main.presenter.contract.MainContract;
import com.mdove.passwordguard.model.net.RealUpdate;
import com.mdove.passwordguard.net.ApiServerImpl;
import com.mdove.passwordguard.update.UpdateDialog;
import com.mdove.passwordguard.utils.log.LogUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;

/**
 * Created by MDove on 2018/2/10.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.MvpView mView;
    private List<BaseMainModel> mData;
    private PasswordDao mDao;
    private DeletedPasswordDao mDeleteDao;

    @Override
    public void subscribe(MainContract.MvpView view) {
        mView = view;
        mData = new ArrayList<>();
        mDao = App.getDaoSession().getPasswordDao();
        mDeleteDao = App.getDaoSession().getDeletedPasswordDao();
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

        MainSearchModel mainSearchModel = new MainSearchModel();
        mainSearchModel.mType = 1;
        mData.add(mainSearchModel);

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

    @Override
    public void onClickBtnLock() {
        mView.onClickBtnLock();
    }

    @Override
    public void onClickBtnDelete() {
        mView.onClickBtnDelete();
    }

    @Override
    public void onClickBtnSearch() {
        mView.onClickBtnSearch();
    }

    @Override
    public void querySearch(String queryKey) {
        List<Password> list = mDao.queryBuilder().whereOr(PasswordDao.Properties.MTitle.like("%" + queryKey + "%"),
                PasswordDao.Properties.MUserName.like("%" + queryKey + "%"),
                PasswordDao.Properties.MPassword.like("%" + queryKey + "%")).list();
        if (list.size()>0){
            mView.searchReturn(list,null);
            return;
        }
        mView.searchReturn(null,"@");

    }

    @Override
    public void deletePassword(int position, Password password) {
        DeletedPassword deletedPassword = DeletedPasswordHelper.getDeletedPassword(password);
        mDeleteDao.insert(deletedPassword);
        mDao.delete(password);

        mView.deletePassword(position);
    }

    @Override
    public void checkUpdate(String version) {
        ApiServerImpl.checkUpdate(version).subscribe(new Subscriber<RealUpdate>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("aaa",e.getMessage());
            }

            @Override
            public void onNext(RealUpdate realUpdate) {
                switch (realUpdate.getCheck()) {
                    case "true": {
                        showUpgradeDialog(realUpdate);
                        break;
                    }
                }
            }
        });
    }

    private void showUpgradeDialog(final RealUpdate result) {
        new UpdateDialog(mView.getContext(), result.getSrc()).show();
    }

    @Override
    public void alterPassword(AlterPasswordModel model, int itemPosition) {
        mDao.update(model.mOldPassword);
        mDao.insert(model.mNewPassword);
        mData.add(new PasswordModel(model.mNewPassword));

        //直接更换旧model的isNew数据（引用指向的内存不变）
        PasswordModel oldModel = (PasswordModel) mData.get(itemPosition);
        oldModel.mIsNew = false;

        mView.alterPasswordSuc(itemPosition, mData.size());
    }
}
