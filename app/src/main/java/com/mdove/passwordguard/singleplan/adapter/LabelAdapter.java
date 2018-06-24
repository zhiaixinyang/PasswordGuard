package com.mdove.passwordguard.singleplan.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemLabelDailySelfBinding;
import com.mdove.passwordguard.databinding.ItemLabelDailySelfSettingBinding;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.MainSelfTaskEtDialog;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.MainSelfTaskEtDialogAdapter;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModel;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModelVM;
import com.mdove.passwordguard.singleplan.EtSinglePlanActivity;
import com.mdove.passwordguard.task.presenter.LabelSettingPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public class LabelAdapter extends RecyclerView.Adapter<LabelAdapter.ViewHolder> {
    private Context mContext;
    private List<DailyTaskLabelModel> mData;
    private EtSinglePlanActivity.OnClickLabelSelectListener mListener;

    public LabelAdapter(Context context) {
        mContext = context;
    }

    @Override
    public LabelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LabelAdapter.ViewHolder((ItemLabelDailySelfBinding) InflateUtils.bindingInflate(parent, R.layout.item_label_daily_self));
    }

    @Override
    public void onBindViewHolder(LabelAdapter.ViewHolder holder, int position) {
        DailyTaskLabelModel mode = mData.get(position);
        holder.bind(mode);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemLabelDailySelfBinding mBinding;

        public ViewHolder(ItemLabelDailySelfBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(final DailyTaskLabelModel model) {
            mBinding.setVm(new DailyTaskLabelModelVM(model));
            mBinding.tvLabel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (model.isSelect) {
                        if (mListener != null) {
                            mListener.onClickLabel(model.mLabelName, model.mLabel.id);
                        }
                    } else {
                        for (DailyTaskLabelModel model1 : mData) {
                            model1.isSelect = false;
                        }
                        model.isSelect = true;
                        notifyDataSetChanged();
                        mListener.onClickLabel(model.mLabelName, model.mLabel.id);
                    }
                }
            });
            if (model.isSelect) {
                if (mListener != null) {
                    mListener.onClickLabel(model.mLabelName, model.mLabel.id);
                }
                mBinding.tvLabel.setBackgroundResource(R.drawable.bg_label_daily_self_select);
                mBinding.tvLabel.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            } else {
                mBinding.tvLabel.setBackgroundResource(R.drawable.bg_label_daily_self_unselect);
                mBinding.tvLabel.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            }
        }
    }

    public void setListener(EtSinglePlanActivity.OnClickLabelSelectListener listener) {
        mListener = listener;
    }

    public void setData(List<DailyTaskLabelModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void notify(int position) {
        notifyItemChanged(position);
    }
}
