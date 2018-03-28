package com.mdove.passwordguard.task.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;
import com.mdove.passwordguard.databinding.ItemSelfTaskDeleteBinding;
import com.mdove.passwordguard.databinding.ItemSelfTaskSucBinding;
import com.mdove.passwordguard.task.model.DeleteSelfTaskModel;
import com.mdove.passwordguard.task.model.DeleteSelfTaskModelVM;
import com.mdove.passwordguard.task.model.SucSelfTaskModel;
import com.mdove.passwordguard.task.model.SucSelfTaskModelVM;
import com.mdove.passwordguard.task.presenter.SucSelfTaskPresenter;
import com.mdove.passwordguard.task.utils.SelfTaskPriorityHelper;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/3/27.
 */

public class SucSelfTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private SucSelfTaskPresenter mPresenter;
    private Context mContext;
    private List<SucSelfTaskModel> mData;
    private OnChangeDataSizeListener mListener;

    public SucSelfTaskAdapter(Context context, SucSelfTaskPresenter presenter) {
        mContext = context;
        mPresenter = presenter;

        registerAdapterDataObserver(mObserver);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SucSelfTaskViewHolder((ItemSelfTaskSucBinding) InflateUtils.bindingInflate(parent, R.layout.item_self_task_suc));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SucSelfTaskModel deleteSelfTaskModel = mData.get(position);
        ((SucSelfTaskViewHolder) holder).bind(deleteSelfTaskModel, position);
    }

    public void setData(List<SucSelfTaskModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public List<SucSelfTaskModel> getData() {
        return mData;
    }

    public void onClickTaskSuc(int position, boolean isSuc) {
        if (isSuc) {
            notifyPosition(position);
        } else {
            mData.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mData.size());
        }
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

    public class SucSelfTaskViewHolder extends RecyclerView.ViewHolder {
        private ItemSelfTaskSucBinding mBinding;

        public SucSelfTaskViewHolder(ItemSelfTaskSucBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(SucSelfTaskModel selfTaskModel, int position) {
            mBinding.setViewModel(new SucSelfTaskModelVM(selfTaskModel, position));
            mBinding.tvTitle.setTextColor(SelfTaskPriorityHelper.getPriorityTextColor(mContext, selfTaskModel.mPriority));
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
