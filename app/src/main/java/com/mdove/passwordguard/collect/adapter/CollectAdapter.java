package com.mdove.passwordguard.collect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;
import com.mdove.passwordguard.collect.model.CollectDailySelfHandler;
import com.mdove.passwordguard.collect.model.CollectDailySelfModel;
import com.mdove.passwordguard.collect.model.CollectDailySelfModelVM;
import com.mdove.passwordguard.collect.presenter.CollectPresenter;
import com.mdove.passwordguard.databinding.ItemCollectDailyselfBinding;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/3/28.
 */

public class CollectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private CollectPresenter mPresenter;
    private Context mContext;
    private List<BaseMainModel> mData;
    private OnChangeDataSizeListener mListener;

    public CollectAdapter(Context context, CollectPresenter presenter) {
        mContext = context;
        mPresenter = presenter;

        registerAdapterDataObserver(mObserver);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectDailySelfViewHolder((ItemCollectDailyselfBinding) InflateUtils.bindingInflate(parent, R.layout.item_collect_dailyself));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CollectDailySelfModel collectDailySelfModel = (CollectDailySelfModel) mData.get(position);
        ((CollectDailySelfViewHolder) holder).bind(collectDailySelfModel, position);
    }

    public void setData(List<BaseMainModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public List<BaseMainModel> getData() {
        return mData;
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class CollectDailySelfViewHolder extends RecyclerView.ViewHolder {
        private ItemCollectDailyselfBinding mBinding;

        public CollectDailySelfViewHolder(ItemCollectDailyselfBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(CollectDailySelfModel collectDailySelfModel, int position) {
            mBinding.setViewModel(new CollectDailySelfModelVM(collectDailySelfModel, position));
            mBinding.setActionHandler(new CollectDailySelfHandler(mPresenter));
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
