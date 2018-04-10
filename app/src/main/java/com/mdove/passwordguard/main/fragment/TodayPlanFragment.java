package com.mdove.passwordguard.main.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentTodayPlanBinding;
import com.mdove.passwordguard.main.adapter.TodayPlanAdapter;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.presenter.TodayPlanPresenter;
import com.mdove.passwordguard.main.presenter.contract.TodayPlanContract;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.List;

/**
 * Created by MDove on 2018/4/9.
 */
public class TodayPlanFragment extends Fragment implements TodayPlanContract.MvpView {
    private FragmentTodayPlanBinding mBinding;
    private EditText mEt;
    private ImageView mBtn;
    private RecyclerView mRlv;
    private TodayPlanPresenter mPresenter;
    private TodayPlanAdapter mAdapter;
    private List<DailyPlanModel> mData;

    public static TodayPlanFragment newInstance() {
        TodayPlanFragment fragment = new TodayPlanFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_today_plan, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEt = mBinding.etToday;
        mBtn = mBinding.btnSend;
        mRlv = mBinding.rlvMainToday;

        mPresenter = new TodayPlanPresenter();
        mPresenter.subscribe(this);
        mAdapter = new TodayPlanAdapter(getContext(), mPresenter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRlv.setLayoutManager(linearLayoutManager);
        mRlv.setAdapter(mAdapter);
        mPresenter.initData();

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEt.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    mPresenter.addDailyPlan(content);
                    mEt.setText("");
                    ToastHelper.shortToast("添加成功");
                    return;
                }
                ToastHelper.shortToast("怎么能添加一个空的工作内容");
            }
        });
    }

    @Override
    public void initData(List<DailyPlanModel> data) {
        mData = data;
        mAdapter.setData(mData);
    }

    @Override
    public void addDailyPlan(int position) {
        mAdapter.notifyPosition(position);
    }

    @Override
    public void updateLostOrGet(int position) {
        mAdapter.notifyPosition(position);
    }
}
