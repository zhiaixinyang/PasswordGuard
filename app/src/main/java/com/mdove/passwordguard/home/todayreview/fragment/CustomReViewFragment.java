package com.mdove.passwordguard.home.todayreview.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentCustomReviewBinding;
import com.mdove.passwordguard.home.todayreview.adapter.CustomReViewAdapter;
import com.mdove.passwordguard.home.todayreview.model.CustomReViewModel;
import com.mdove.passwordguard.home.todayreview.presenter.CustomReViewPresenter;
import com.mdove.passwordguard.home.todayreview.presenter.contract.CustomReViewContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/30.
 */

public class CustomReViewFragment extends Fragment implements CustomReViewContract.MvpView {
    private FragmentCustomReviewBinding mBinding;
    private CustomReViewPresenter mPresenter;
    private CustomReViewAdapter mAdapter;

    public static CustomReViewFragment newInstance() {
        Bundle args = new Bundle();

        CustomReViewFragment fragment = new CustomReViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_custom_review, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new CustomReViewPresenter();
        mPresenter.subscribe(this);

        mAdapter = new CustomReViewAdapter(getContext(), new ArrayList<CustomReViewModel>());
        mBinding.rlvReview.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rlvReview.setAdapter(mAdapter);

        mPresenter.initData();

        mBinding.btnInRichEdit.setText(Html.fromHtml("<u>使用Plus编辑器编辑</u>"));

        mBinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mBinding.etContent.getText().toString();
                mBinding.etContent.setText("");
                mPresenter.addCustomSchedule(content);
            }
        });

        mBinding.etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    mBinding.btnSend.setEnabled(false);
                    mBinding.btnSend.setColorFilter(ContextCompat.getColor(getContext(), R.color.gray));
                } else {
                    mBinding.btnSend.setEnabled(true);
                    mBinding.btnSend.setColorFilter(ContextCompat.getColor(getContext(), R.color.blue_700));
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    @Override
    public void initData(List<CustomReViewModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void addCustomSchedule(int position) {
        mAdapter.notifyItemChanged(position);
    }
}
