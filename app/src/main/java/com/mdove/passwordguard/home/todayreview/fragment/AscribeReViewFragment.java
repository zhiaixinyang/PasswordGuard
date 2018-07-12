package com.mdove.passwordguard.home.todayreview.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentAscribeReviewBinding;
import com.mdove.passwordguard.home.todayreview.adapter.InnerAscribeAdapter;
import com.mdove.passwordguard.home.todayreview.adapter.OuterAscribeAdapter;
import com.mdove.passwordguard.home.todayreview.model.InnerAscribeModel;
import com.mdove.passwordguard.home.todayreview.model.OuterAscribeModel;
import com.mdove.passwordguard.home.todayreview.presenter.AscribeReViewPresenter;
import com.mdove.passwordguard.home.todayreview.presenter.contract.AscribeReViewContract;
import com.mdove.passwordguard.utils.InflateUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/7/12.
 */

public class AscribeReViewFragment extends Fragment implements AscribeReViewContract.MvpView {
    private FragmentAscribeReviewBinding mBinding;
    private AscribeReViewPresenter mPresenter;
    private InnerAscribeAdapter mInnerAdapter;
    private OuterAscribeAdapter mOuterAdapter;
    private List<InnerAscribeModel> innerAscribeModels;
    private List<OuterAscribeModel> outerAscribeModels;
    private String mTitle;

    public static AscribeReViewFragment newInstance() {
        Bundle args = new Bundle();

        AscribeReViewFragment fragment = new AscribeReViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = InflateUtils.bindingInflate(container, R.layout.fragment_ascribe_review);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new AscribeReViewPresenter();
        mPresenter.subscribe(this);
        innerAscribeModels = new ArrayList<>();
        outerAscribeModels = new ArrayList<>();
        mPresenter.initData(innerAscribeModels, outerAscribeModels);

        mInnerAdapter = new InnerAscribeAdapter(getContext());
        mInnerAdapter.setData(innerAscribeModels);
        mOuterAdapter = new OuterAscribeAdapter(getContext());
        mOuterAdapter.setDate(outerAscribeModels);

        initListener();
    }

    private void initListener() {
        mBinding.etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTitle = s.toString();
            }
        });

        mBinding.btnInnerSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inner = mBinding.etInner.getText().toString();
                if (!TextUtils.isEmpty(inner) && !TextUtils.isEmpty(mTitle)) {
                    mBinding.etInner.setText("");
                    mPresenter.addInnerAscribe(inner, mTitle);
                }else{
                    ToastHelper.shortToast(getString(R.string.string_inner_empty));
                }
            }
        });

        mBinding.btnOuterSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String outer = mBinding.etOuter.getText().toString();
                if (!TextUtils.isEmpty(outer) && !TextUtils.isEmpty(mTitle)) {
                    mBinding.etInner.setText("");
                    mPresenter.addOuterAscribe(outer, mTitle);
                }else{
                    ToastHelper.shortToast(getString(R.string.string_outer_empty));
                }
            }
        });
    }

    @Override
    public void onAddInnerAscribe(int position) {
        mInnerAdapter.notifyItemChanged(position);
    }

    @Override
    public void onAddOuterAscribe(int position) {
        mOuterAdapter.notifyItemChanged(position);
    }
}
