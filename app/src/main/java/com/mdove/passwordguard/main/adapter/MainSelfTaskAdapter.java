package com.mdove.passwordguard.main.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemMainSelfTaskRlvBinding;
import com.mdove.passwordguard.databinding.ItemSelfTaskBinding;
import com.mdove.passwordguard.main.model.handler.MainSelfTaskHandler;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;
import com.mdove.passwordguard.task.model.handle.SelfTaskHandler;
import com.mdove.passwordguard.task.presenter.SelfTaskPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/3/25.
 */

public class MainSelfTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private MainPresenter mPresenter;
    private Context mContext;
    private List<SelfTaskModel> mData;

    public MainSelfTaskAdapter(Context context, MainPresenter presenter, List<SelfTaskModel> data) {
        mContext = context;
        mPresenter = presenter;
        mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelfTaskRlvViewHolder((ItemMainSelfTaskRlvBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_self_task_rlv));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SelfTaskModel selfTaskModel = mData.get(position);
        ((SelfTaskRlvViewHolder) holder).bind(selfTaskModel, position);
    }

    public void setData(List<SelfTaskModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void onClickTaskSuc(int position){
        notifyPosition(position);
    }

    public void notifyPosition(int position) {
        notifyInsertPosition(position);
    }

    public void notifyInsertPosition(int position) {
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class SelfTaskRlvViewHolder extends RecyclerView.ViewHolder {
        private ItemMainSelfTaskRlvBinding mBinding;

        public SelfTaskRlvViewHolder(ItemMainSelfTaskRlvBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(SelfTaskModel selfTaskModel, int position) {
            mBinding.setViewModel(new SelfTaskModelVM(selfTaskModel, position));
            mBinding.setActionHandler(new MainSelfTaskHandler(mPresenter,MainSelfTaskAdapter.this));
            if (selfTaskModel.mIsSuc) {
                mBinding.tvTitle.getPaint().setAntiAlias(true);
                mBinding.tvTitle.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_off);
                mBinding.tvBtn.setText("取消");
                mBinding.tvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.gray_light));
            } else {
                mBinding.tvTitle.getPaint().setFlags(0);
                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_on);
                mBinding.tvBtn.setText("完成");
                mBinding.tvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            }
        }
    }
}
