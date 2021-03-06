package com.mdove.passwordguard.alldata.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.alldata.model.AllPasswordModel;
import com.mdove.passwordguard.alldata.model.handler.AllPasswordHandler;
import com.mdove.passwordguard.alldata.model.vm.ItemAllPasswordVM;
import com.mdove.passwordguard.alldata.presenter.AllPasswordPresenter;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;
import com.mdove.passwordguard.databinding.ItemAllPasswordBinding;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/4/6.
 */

public class AllPasswordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<AllPasswordModel> mData;
    private Context mContext;
    private AllPasswordPresenter mPresenter;
    private OnChangeDataSizeListener mListener;

    public AllPasswordAdapter(Context context, List<AllPasswordModel> data, AllPasswordPresenter presenter) {
        mContext = context;
        mData = data;
        mPresenter = presenter;
        registerAdapterDataObserver(mObserver);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemAllPasswordBinding) InflateUtils.bindingInflate(parent, R.layout.item_all_password));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AllPasswordModel model = mData.get(position);
        ((ViewHolder) holder).bind(new ItemAllPasswordVM(model, position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemAllPasswordBinding mBinding;

        public ViewHolder(ItemAllPasswordBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
        }

        public void bind(ItemAllPasswordVM vm) {
            mBinding.setViewModel(vm);
            mBinding.setActionHandler(new AllPasswordHandler(mPresenter));
        }
    }

    public void setData(List<AllPasswordModel> data) {
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
