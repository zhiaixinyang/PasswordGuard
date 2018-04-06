package com.mdove.passwordguard.task.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;
import com.mdove.passwordguard.databinding.ItemSelfTaskDeleteBinding;
import com.mdove.passwordguard.task.model.DeleteSelfTaskModel;
import com.mdove.passwordguard.task.model.DeleteSelfTaskModelVM;
import com.mdove.passwordguard.task.presenter.DeleteSelfTaskPresenter;
import com.mdove.passwordguard.task.utils.SelfTaskPriorityHelper;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/3/27.
 */

public class DeleteSelfTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private DeleteSelfTaskPresenter mPresenter;
    private Context mContext;
    private List<DeleteSelfTaskModel> mData;
    private OnChangeDataSizeListener mListener;

    public DeleteSelfTaskAdapter(Context context, DeleteSelfTaskPresenter presenter) {
        mContext = context;
        mPresenter = presenter;

        registerAdapterDataObserver(mObserver);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DeleteSelfTaskViewHolder((ItemSelfTaskDeleteBinding) InflateUtils.bindingInflate(parent, R.layout.item_self_task_delete));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DeleteSelfTaskModel deleteSelfTaskModel = mData.get(position);
        ((DeleteSelfTaskViewHolder) holder).bind(deleteSelfTaskModel, position);
    }

    public void setData(List<DeleteSelfTaskModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public List<DeleteSelfTaskModel> getData() {
        return mData;
    }

    public void onClickTaskSuc(int position) {
        notifyPosition(position);
    }

    public void onClickTaskDelete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

    public void notifyDeleteReturn(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

    public void notifyPosition(int position) {
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class DeleteSelfTaskViewHolder extends RecyclerView.ViewHolder {
        private ItemSelfTaskDeleteBinding mBinding;

        public DeleteSelfTaskViewHolder(ItemSelfTaskDeleteBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(DeleteSelfTaskModel selfTaskModel, int position) {
            mBinding.setViewModel(new DeleteSelfTaskModelVM(selfTaskModel, position));
            mBinding.setActionHandler(mPresenter);
            mBinding.tvTitle.setTextColor(SelfTaskPriorityHelper.getPriorityTextColor(mContext, selfTaskModel.mPriority));

            if (selfTaskModel.mIsSuc) {
                mBinding.tvTitle.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                mBinding.tvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.gray_light));
            } else {
                mBinding.tvTitle.getPaint().setFlags(0);
                mBinding.tvTitle.getPaint().setAntiAlias(true);
                mBinding.tvTitle.setTextColor(SelfTaskPriorityHelper.getPriorityTextColor(mContext, selfTaskModel.mPriority));
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
