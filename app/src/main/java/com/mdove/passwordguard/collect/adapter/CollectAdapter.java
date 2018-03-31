package com.mdove.passwordguard.collect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;
import com.mdove.passwordguard.collect.model.CollectDailySelfHandler;
import com.mdove.passwordguard.collect.model.CollectDailySelfModel;
import com.mdove.passwordguard.collect.model.CollectDailySelfModelVM;
import com.mdove.passwordguard.collect.model.CollectPasswordHandler;
import com.mdove.passwordguard.collect.model.CollectPasswordModel;
import com.mdove.passwordguard.collect.model.CollectPasswordModelVM;
import com.mdove.passwordguard.collect.presenter.CollectPresenter;
import com.mdove.passwordguard.databinding.ItemCollectDailyselfBinding;
import com.mdove.passwordguard.databinding.ItemCollectPasswordNormalBinding;
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
    private static final int TYPE_PASSWORD = 0;
    private static final int TYPE_DAILY_SELF = 1;

    public CollectAdapter(Context context, CollectPresenter presenter) {
        mContext = context;
        mPresenter = presenter;

        registerAdapterDataObserver(mObserver);
    }

    @Override
    public int getItemViewType(int position) {
        BaseMainModel baseMainModel = mData.get(position);
        if (baseMainModel instanceof CollectPasswordModel) {
            return TYPE_PASSWORD;
        } else if (baseMainModel instanceof CollectDailySelfModel) {
            return TYPE_DAILY_SELF;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_DAILY_SELF: {
                return new CollectDailySelfViewHolder((ItemCollectDailyselfBinding) InflateUtils.bindingInflate(parent, R.layout.item_collect_dailyself));
            }
            case TYPE_PASSWORD: {
                return new CollectPasswordViewHolder((ItemCollectPasswordNormalBinding) InflateUtils.bindingInflate(parent, R.layout.item_collect_password_normal));
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseMainModel baseMainModel = mData.get(position);
        if (baseMainModel instanceof CollectDailySelfModel) {
            CollectDailySelfModel collectDailySelfModel = (CollectDailySelfModel) baseMainModel;
            ((CollectDailySelfViewHolder) holder).bind(collectDailySelfModel, position);
        }else if (baseMainModel instanceof CollectPasswordModel){
            CollectPasswordModel collectPasswordModel = (CollectPasswordModel) baseMainModel;
            ((CollectPasswordViewHolder) holder).bind(collectPasswordModel, position);
        }
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

    public class CollectPasswordViewHolder extends RecyclerView.ViewHolder {
        private ItemCollectPasswordNormalBinding mBinding;

        public CollectPasswordViewHolder(ItemCollectPasswordNormalBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(CollectPasswordModel collectPasswordModel, int position) {
            mBinding.setViewModel(new CollectPasswordModelVM(collectPasswordModel, position));
            mBinding.setActionHandler(new CollectPasswordHandler(mPresenter));
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
