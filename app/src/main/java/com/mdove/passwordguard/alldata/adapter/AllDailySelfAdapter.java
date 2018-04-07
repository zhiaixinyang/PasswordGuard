package com.mdove.passwordguard.alldata.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.alldata.model.AllDailySelfModel;
import com.mdove.passwordguard.alldata.model.AllPasswordModel;
import com.mdove.passwordguard.alldata.model.handler.AllDailySelfHandler;
import com.mdove.passwordguard.alldata.model.handler.AllPasswordHandler;
import com.mdove.passwordguard.alldata.model.vm.ItemAllDailySelfVM;
import com.mdove.passwordguard.alldata.model.vm.ItemAllPasswordVM;
import com.mdove.passwordguard.alldata.presenter.AllDailySelfPresenter;
import com.mdove.passwordguard.alldata.presenter.AllPasswordPresenter;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;
import com.mdove.passwordguard.databinding.ItemAllDailyselfBinding;
import com.mdove.passwordguard.databinding.ItemAllPasswordBinding;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/4/7.
 */

public class AllDailySelfAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<AllDailySelfModel> mData;
    private Context mContext;
    private AllDailySelfPresenter mPresenter;
    private OnChangeDataSizeListener mListener;

    public AllDailySelfAdapter(Context context, List<AllDailySelfModel> data, AllDailySelfPresenter presenter) {
        mContext = context;
        mData = data;
        mPresenter = presenter;
        registerAdapterDataObserver(mObserver);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemAllDailyselfBinding) InflateUtils.bindingInflate(parent, R.layout.item_all_dailyself));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AllDailySelfModel model = mData.get(position);
        ((ViewHolder) holder).bind(new ItemAllDailySelfVM(model, position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemAllDailyselfBinding mBinding;

        public ViewHolder(ItemAllDailyselfBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
        }

        public void bind(ItemAllDailySelfVM vm) {
            mBinding.setViewModel(vm);
            mBinding.setActionHandler(new AllDailySelfHandler(mPresenter));
        }
    }

    public void setData(List<AllDailySelfModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void notifyPosition(int position) {
        notifyItemChanged(position);
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
