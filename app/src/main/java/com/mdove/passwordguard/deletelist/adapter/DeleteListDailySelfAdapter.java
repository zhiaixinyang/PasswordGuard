package com.mdove.passwordguard.deletelist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;
import com.mdove.passwordguard.databinding.ItemDeleteDailySelfBinding;
import com.mdove.passwordguard.databinding.ItemDeleteListTopBinding;
import com.mdove.passwordguard.databinding.ItemDeletePasswordBinding;
import com.mdove.passwordguard.deletelist.model.DeleteDailySelfModel;
import com.mdove.passwordguard.deletelist.model.DeletePasswordModel;
import com.mdove.passwordguard.deletelist.model.DeleteTopModel;
import com.mdove.passwordguard.deletelist.model.vm.DeleteDailySelfModelVM;
import com.mdove.passwordguard.deletelist.model.vm.DeletePasswordModelVM;
import com.mdove.passwordguard.deletelist.model.vm.ItemDeleteListTopVM;
import com.mdove.passwordguard.deletelist.presenter.DeleteListDailySelfPresenter;
import com.mdove.passwordguard.deletelist.presenter.DeleteListPasswordPresenter;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/2/25.
 */

public class DeleteListDailySelfAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BaseMainModel> mData;
    private DeleteListDailySelfPresenter mPresenter;
    private static final int TYPE_MAIN_DELETE_DAILY_SELF = 0;
    private static final int TYPE_MAIN_TOP = 1;

    private OnChangeDataSizeListener mListener;

    public DeleteListDailySelfAdapter(DeleteListDailySelfPresenter presenter) {
        mPresenter = presenter;
        registerAdapterDataObserver(mObserver);
    }

    @Override
    public int getItemViewType(int position) {
        BaseMainModel model = mData.get(position);
        switch (model.mType) {
            case 1: {
                if (model instanceof DeleteTopModel) {
                    return TYPE_MAIN_TOP;
                } else if (model instanceof DeleteDailySelfModel) {
                    return TYPE_MAIN_DELETE_DAILY_SELF;
                }
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_MAIN_TOP: {
                return new DeleteTopViewHolder((ItemDeleteListTopBinding) InflateUtils.bindingInflate(parent, R.layout.item_delete_list_top));
            }
            case TYPE_MAIN_DELETE_DAILY_SELF: {
                return new DeleteDailySelfViewHolder((ItemDeleteDailySelfBinding) InflateUtils.bindingInflate(parent, R.layout.item_delete_daily_self));
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseMainModel model = mData.get(position);
        if (holder instanceof DeleteDailySelfViewHolder) {
            ((DeleteDailySelfViewHolder) holder).bind((DeleteDailySelfModel) model, mPresenter, position);
        } else if (holder instanceof DeleteTopViewHolder) {
            DeleteTopModel deleteTopModel = (DeleteTopModel) model;
            deleteTopModel.mAllDeleteSize = mData.size() - 1;
            ((DeleteTopViewHolder) holder).bind(new ItemDeleteListTopVM(deleteTopModel));
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<BaseMainModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void notifyDeleteReturn(int position) {
        //更新第一条Item（计数Item）
        notifyItemChanged(0);
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

    public class DeleteDailySelfViewHolder extends RecyclerView.ViewHolder {
        private ItemDeleteDailySelfBinding mBinding;

        public DeleteDailySelfViewHolder(ItemDeleteDailySelfBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(DeleteDailySelfModel model, DeleteListDailySelfPresenter presenter, int position) {
            mBinding.setViewModel(new DeleteDailySelfModelVM(model, position));
            mBinding.setPresenter(presenter);
        }
    }

    public class DeleteTopViewHolder extends RecyclerView.ViewHolder {
        private ItemDeleteListTopBinding mBinding;

        public DeleteTopViewHolder(ItemDeleteListTopBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(ItemDeleteListTopVM vm) {
            mBinding.setViewModel(vm);
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
