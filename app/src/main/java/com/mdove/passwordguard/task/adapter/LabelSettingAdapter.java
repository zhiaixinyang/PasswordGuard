package com.mdove.passwordguard.task.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemLabelDailySelfSettingBinding;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.MainSelfTaskEtDialog;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModel;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModelVM;
import com.mdove.passwordguard.task.presenter.LabelSettingPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/5/28.
 */

public class LabelSettingAdapter extends RecyclerView.Adapter<LabelSettingAdapter.ViewHolder> {
    private Context mContext;
    private List<DailyTaskLabelModel> mData;
    private MainSelfTaskEtDialog.OnClickLabelSelectListener mListener;
    private LabelSettingPresenter mPresenter;

    public LabelSettingAdapter(Context context, LabelSettingPresenter presenter) {
        mContext = context;
        mPresenter = presenter;
    }

    @Override
    public LabelSettingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LabelSettingAdapter.ViewHolder((ItemLabelDailySelfSettingBinding) InflateUtils.bindingInflate(parent, R.layout.item_label_daily_self_setting));
    }

    @Override
    public void onBindViewHolder(LabelSettingAdapter.ViewHolder holder, int position) {
        DailyTaskLabelModel mode = mData.get(position);
        holder.bind(mode);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemLabelDailySelfSettingBinding mBinding;

        public ViewHolder(ItemLabelDailySelfSettingBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(final DailyTaskLabelModel model) {
            mBinding.setVm(new DailyTaskLabelModelVM(model));
            mBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.deleteLabel(model.mLabel.id);
                }
            });
        }
    }

    public void setListener(MainSelfTaskEtDialog.OnClickLabelSelectListener listener) {
        mListener = listener;
    }

    public void setData(List<DailyTaskLabelModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void notifyDelete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

    public void notify(int position) {
        notifyItemChanged(position);
    }
}
