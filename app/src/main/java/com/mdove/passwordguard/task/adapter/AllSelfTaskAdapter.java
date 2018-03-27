package com.mdove.passwordguard.task.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemAllSelfTaskBinding;
import com.mdove.passwordguard.databinding.ItemSelfTaskBinding;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;
import com.mdove.passwordguard.task.model.handle.AllSelfTaskHandler;
import com.mdove.passwordguard.task.model.handle.SelfTaskHandler;
import com.mdove.passwordguard.task.presenter.AllSelfTaskPresenter;
import com.mdove.passwordguard.task.presenter.SelfTaskPresenter;
import com.mdove.passwordguard.task.utils.SelfTaskPriorityHelper;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/3/27.
 */

public class AllSelfTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private AllSelfTaskPresenter mPresenter;
    private Context mContext;
    private List<SelfTaskModel> mData;

    public AllSelfTaskAdapter(Context context, AllSelfTaskPresenter presenter) {
        mContext = context;
        mPresenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelfTaskViewHolder((ItemAllSelfTaskBinding) InflateUtils.bindingInflate(parent, R.layout.item_all_self_task));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SelfTaskModel selfTaskModel = mData.get(position);
        ((SelfTaskViewHolder) holder).bind(selfTaskModel, position);
    }

    public void setData(List<SelfTaskModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void notifyPosition(int position) {
        notifyInsertPosition(position);
    }

    public void notifyInsertPosition(int position) {
        notifyItemChanged(position);
    }

    public void notifyDeleteSelfTask(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class SelfTaskViewHolder extends RecyclerView.ViewHolder {
        private ItemAllSelfTaskBinding mBinding;

        public SelfTaskViewHolder(ItemAllSelfTaskBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(SelfTaskModel selfTaskModel, int position) {
            mBinding.setViewModel(new SelfTaskModelVM(selfTaskModel, position));
            mBinding.setActionHandler(new AllSelfTaskHandler(mPresenter));

            mBinding.ivPriority.setColorFilter(SelfTaskPriorityHelper.getPriorityBtnColor(mContext,selfTaskModel.mPriority), PorterDuff.Mode.SRC_ATOP);
            mBinding.tvPriorityTip.setTextColor(SelfTaskPriorityHelper.getPriorityBtnColor(mContext,selfTaskModel.mPriority));
            mBinding.tvTitle.setTextColor(SelfTaskPriorityHelper.getPriorityTextColor(mContext,selfTaskModel.mPriority));

            if (selfTaskModel.mIsSuc) {
                mBinding.tvTitle.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                mBinding.tvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.gray_light));

                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_off);
                mBinding.tvBtn.setText("取消");
            } else {
                mBinding.tvTitle.getPaint().setFlags(0);
                mBinding.tvTitle.getPaint().setAntiAlias(true);
                mBinding.tvTitle.setTextColor(SelfTaskPriorityHelper.getPriorityTextColor(mContext,selfTaskModel.mPriority));

                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_on);
                mBinding.tvBtn.setText("完成");
            }
            if (selfTaskModel.mIsSee) {
                mBinding.ivSee.setImageResource(R.mipmap.ic_self_task_see_on);
                mBinding.tvSeeTip.setTextColor(ContextCompat.getColor(mContext, R.color.commonColor));
            } else {
                mBinding.ivSee.setImageResource(R.mipmap.ic_self_task_see_off);
                mBinding.tvSeeTip.setTextColor(ContextCompat.getColor(mContext, R.color.gray_light));
            }
        }
    }
}
