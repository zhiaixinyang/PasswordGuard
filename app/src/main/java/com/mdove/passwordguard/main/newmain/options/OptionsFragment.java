package com.mdove.passwordguard.main.newmain.options;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentOptionsBinding;
import com.mdove.passwordguard.main.model.MainOptionInfo;
import com.mdove.passwordguard.main.newmain.options.adapter.OptionsOtherAdapter;
import com.mdove.passwordguard.main.newmain.options.model.MainOptionNewInfo;
import com.mdove.passwordguard.main.newmain.options.model.MainOptionsTopModel;
import com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter;
import com.mdove.passwordguard.main.newmain.options.presenter.contract.OptionsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/5/3.
 */

public class OptionsFragment extends Fragment implements OptionsContract.MvpView {
    private FragmentOptionsBinding mBinding;
    private OptionsPresenter mPresenter;
    private OptionsOtherAdapter mAdapter;

    public static OptionsFragment newInstance() {

        Bundle args = new Bundle();

        OptionsFragment fragment = new OptionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_options, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new OptionsPresenter();
        mPresenter.subscribe(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new OptionsOtherAdapter(getContext(), new ArrayList<MainOptionNewInfo>(), mPresenter);
        mBinding.rlvOptions.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rlvOptions.setAdapter(mAdapter);

        mPresenter.initData();
    }

    @Override
    public void showTopOptions(MainOptionsTopModel model) {
        mBinding.setTopOneVM(model.mOneVM);
        mBinding.setTopTwoVM(model.mTwoVM);
        mBinding.setTopThreeVM(model.mThreeVM);
    }

    @Override
    public void showOtherOptions(List<MainOptionNewInfo> data) {
        mAdapter.setData(data);
    }
}
