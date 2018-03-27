package com.mdove.passwordguard.main.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;
import com.mdove.passwordguard.databinding.ItemMainSelfTaskRlvBinding;
import com.mdove.passwordguard.main.model.handler.MainSelfTaskHandler;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;
import com.mdove.passwordguard.task.utils.SelfTaskPriorityHelper;
import com.mdove.passwordguard.utils.InflateUtils;
import com.mdove.passwordguard.utils.log.LogUtils;

import java.util.List;

/**
 * Created by MDove on 2018/3/25.
 */

public class MainSelfTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private MainPresenter mPresenter;
    private Context mContext;
    private List<SelfTaskModel> mData;
    private OnChangeDataSizeListener mListener;

    public MainSelfTaskAdapter(Context context, MainPresenter presenter, List<SelfTaskModel> data) {
        mContext = context;
        mPresenter = presenter;
        mData = data;

        registerAdapterDataObserver(mObserver);
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

    public List<SelfTaskModel> getData() {
        return mData;
    }

    public void onClickTaskSuc(int position) {
        notifyPosition(position);
    }

    public void onClickTaskPriority(int position) {
        notifyPosition(position);
    }

    public void onClickTaskDelete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

    public void onClickTaskSee(int position, boolean isRemove) {
        if (isRemove) {
            mData.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mData.size());
        } else {
            notifyPosition(position);
        }
    }

    public void notifyPosition(int position) {
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
            mBinding.setActionHandler(new MainSelfTaskHandler(mPresenter));
            mBinding.tvTitle.setTextColor(SelfTaskPriorityHelper.getPriorityTextColor(mContext, selfTaskModel.mPriority));

            if (selfTaskModel.mIsSuc) {
                mBinding.tvTitle.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                mBinding.tvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.gray_light));

                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_off);
                mBinding.tvBtn.setText("取消");
            } else {
                mBinding.tvTitle.getPaint().setFlags(0);
                mBinding.tvTitle.getPaint().setAntiAlias(true);
                mBinding.tvTitle.setTextColor(SelfTaskPriorityHelper.getPriorityTextColor(mContext, selfTaskModel.mPriority));

                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_on);
                mBinding.tvBtn.setText("完成");
            }
        }
    }

    public void setOnChangeDataSizeListener(OnChangeDataSizeListener listener) {
        mListener = listener;
    }

    private RecyclerView.AdapterDataObserver mObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            onDataChange(mData.size());
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            onDataChange(mData.size());
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            onDataChange(mData.size());
        }
    };

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        unregisterAdapterDataObserver(mObserver);
    }

    private void onDataChange(int dataSize) {
        if (mListener != null) {
            if (dataSize <= 0) {
                mListener.dataIsEmpty(true);
            } else {
                mListener.dataIsEmpty(false);
            }
        }
    }
}
