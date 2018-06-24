package com.mdove.passwordguard.singleplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityEtSinglePlanBinding;
import com.mdove.passwordguard.greendao.entity.SinglePlan;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModel;
import com.mdove.passwordguard.main.newmain.dailytask.util.LabelConstant;
import com.mdove.passwordguard.singleplan.adapter.EtSinglePlanAdapter;
import com.mdove.passwordguard.singleplan.adapter.LabelAdapter;
import com.mdove.passwordguard.singleplan.model.EtSinglePlanHandler;
import com.mdove.passwordguard.singleplan.presenter.EtSinglePlanPresenter;
import com.mdove.passwordguard.singleplan.presenter.contract.EtSinglePlanContract;
import com.mdove.passwordguard.ui.renkstar.BubbleSeekBar;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public class EtSinglePlanActivity extends BaseActivity implements EtSinglePlanContract.MvpView {
    private ActivityEtSinglePlanBinding mBinding;
    private RecyclerView mRlvLabel, mRlvPlan;
    private LabelAdapter mLabelAdapter;
    private EtSinglePlanAdapter mEtSinglePlanAdapter;
    private EtSinglePlanPresenter mPresenter;
    private String mSelectLabel = LabelConstant.DEFAULT_LABEL;
    private long mLabelId = -1L;
    private String mSinglePlan;
    private int mImportant, mUrgent;

    public static void start(Context context) {
        Intent start = new Intent(context, EtSinglePlanActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_et_single_plan);

        mRlvLabel = mBinding.rlvLabel;
        mRlvPlan = mBinding.rlvPlan;

        mPresenter = new EtSinglePlanPresenter();
        mBinding.setHandler(new EtSinglePlanHandler(mPresenter));
        mPresenter.subscribe(this);

        mLabelAdapter = new LabelAdapter(this);
        mEtSinglePlanAdapter = new EtSinglePlanAdapter(this);

        mRlvLabel.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false));
        mRlvLabel.setAdapter(mLabelAdapter);
        mRlvPlan.setLayoutManager(new LinearLayoutManager(this));
        mRlvPlan.setAdapter(mEtSinglePlanAdapter);

        mPresenter.initLabel();
        initListener();
    }

    private void initListener() {
        mLabelAdapter.setListener(new OnClickLabelSelectListener() {
            @Override
            public void onClickLabel(String content, long id) {
                mSelectLabel = content;
                mLabelId = id;
            }
        });
        mBinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mBinding.etPlan.getText().toString();

                if (TextUtils.isEmpty(content) && TextUtils.isEmpty(mSinglePlan)) {
                    ToastHelper.shortToast(getString(R.string.string_et_single_plan_fail));
                }

                if (!TextUtils.isEmpty(mSinglePlan)) {
                    if (!TextUtils.isEmpty(content)) {
                        mSinglePlan += "#" + content;
                        mBinding.etPlan.setText("");
                    }

                    SinglePlan plan = new SinglePlan();
                    plan.setMImportant(mImportant);
                    plan.setMUrgent(mUrgent);
                    plan.setMIsSee(0);
                    plan.setMIsSuc(0);
                    plan.setMLabel(mSelectLabel);
                    plan.setMLabelId(mLabelId);
                    plan.setMSinglePlan(mSinglePlan);
                    plan.setMTime(new Date().getTime());

                    mPresenter.addSinglePlan(plan);

                    ToastHelper.shortToast("添加计划成功！");
                }
            }
        });
        mBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mBinding.etPlan.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    if (!TextUtils.isEmpty(mSinglePlan)) {
                        mSinglePlan += "#" + content;
                        mBinding.etPlan.setText("");
                    } else {
                        mSinglePlan = content;
                        mBinding.etPlan.setText("");
                    }
                    String[] arr = mSinglePlan.split("#");
                    if (arr.length == 1) {
                        mEtSinglePlanAdapter.addSinglePlan("主计划", content);
                    } else {
                        mEtSinglePlanAdapter.addSinglePlan("子计划", content);
                    }
                } else {
                    ToastHelper.shortToast(getString(R.string.string_et_single_plan_empty));
                }
            }
        });

        mBinding.bbUrgent.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(int progress, float progressFloat) {
                super.onProgressChanged(progress, progressFloat);
                mUrgent = progress;
            }
        });

        mBinding.bbImportant.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(int progress, float progressFloat) {
                super.onProgressChanged(progress, progressFloat);
                mImportant = progress;
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initLabel(List<DailyTaskLabelModel> data) {
        mLabelAdapter.setData(data);
    }

    public interface OnClickLabelSelectListener {
        void onClickLabel(String content, long id);
    }
}
