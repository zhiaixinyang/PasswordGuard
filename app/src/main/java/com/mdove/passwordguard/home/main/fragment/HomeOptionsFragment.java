package com.mdove.passwordguard.home.main.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentHomeMessBinding;
import com.mdove.passwordguard.databinding.FragmentHomeOptionsBinding;
import com.mdove.passwordguard.home.main.model.HomeOptionsHandler;
import com.mdove.passwordguard.home.main.presenter.HomeOptionsPresenter;
import com.mdove.passwordguard.home.main.presenter.contract.HomeOptionsContract;
import com.mdove.passwordguard.utils.InflateUtils;

public class HomeOptionsFragment extends Fragment implements HomeOptionsContract.MvpView {
    private FragmentHomeOptionsBinding mBinding;
    private HomeOptionsPresenter mPresenter;

    public static HomeOptionsFragment newInstance() {

        Bundle args = new Bundle();

        HomeOptionsFragment fragment = new HomeOptionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_options, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new HomeOptionsPresenter();
        mPresenter.subscribe(this);

        mBinding.setHandler(new HomeOptionsHandler(mPresenter));
    }
}
