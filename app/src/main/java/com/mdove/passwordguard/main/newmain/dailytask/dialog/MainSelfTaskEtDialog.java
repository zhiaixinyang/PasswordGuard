package com.mdove.passwordguard.main.newmain.dailytask.dialog;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.DialogDailyTaskEtBinding;
import com.mdove.passwordguard.greendao.SelfTaskLabelDao;
import com.mdove.passwordguard.greendao.entity.SelfTaskLabel;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModel;
import com.mdove.passwordguard.task.LabelSettingActivity;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 18/5/27.
 */
public class MainSelfTaskEtDialog extends AppCompatDialog {
    private DialogDailyTaskEtBinding mBinding;
    private Context mContext;
    private MainSelfTaskEtDialogAdapter mAdapter;
    private List<DailyTaskLabelModel> mData;
    private SelfTaskLabelDao mDao;
    private OnClickSendListener mOnClickSendListener;
    private OnClickLabelSelectListener mOnClickLabelSelectListener;

    public MainSelfTaskEtDialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        mContext = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_daily_task_et,
                null, false);
        setContentView(mBinding.getRoot());
        mData = new ArrayList<>();
        mAdapter = new MainSelfTaskEtDialogAdapter(mContext, mData);

        mBinding.btnLabelSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LabelSettingActivity.start(mContext);
                dismiss();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDao = App.getDaoSession().getSelfTaskLabelDao();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rlv.setLayoutManager(layoutManager);
        mBinding.rlv.setAdapter(mAdapter);

        initData();
        initListener();
    }

    private void initListener() {
        mBinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickSendListener != null) {
                    String content = mBinding.etDailyTask.getText().toString();
                    if (!TextUtils.isEmpty(content)) {
                        mBinding.etDailyTask.setText("");
                        mOnClickSendListener.onClickSend(content);
                    } else {
                        ToastHelper.shortToast("今日的计划怎么能为空呢？");
                    }
                }
            }
        });
    }

    private void initData() {
        List<SelfTaskLabel> data = mDao.queryBuilder().list();
        for (SelfTaskLabel selfTaskLabel : data) {
            mData.add(new DailyTaskLabelModel(selfTaskLabel));
        }
        if (mData.size() <= 0) {
            return;
        }
        mData.get(0).setSelect(true);
        mAdapter.setData(mData);
    }

    public void setOnClickSendListener(OnClickSendListener onClickSendListener) {
        mOnClickSendListener = onClickSendListener;
    }

    public void setOnClickLabelSelectListener(OnClickLabelSelectListener onClickLabelSelectListener) {
        mOnClickLabelSelectListener = onClickLabelSelectListener;
        mAdapter.setListener(mOnClickLabelSelectListener);
    }

    public interface OnClickSendListener {
        void onClickSend(String content);
    }

    public interface OnClickLabelSelectListener {
        void onClickLabel(String content);
    }
}
