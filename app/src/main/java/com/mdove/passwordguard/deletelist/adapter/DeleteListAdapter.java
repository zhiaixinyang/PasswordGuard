package com.mdove.passwordguard.deletelist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemDeleteListTopBinding;
import com.mdove.passwordguard.databinding.ItemDeletePasswordBinding;
import com.mdove.passwordguard.deletelist.model.DeletePasswordModel;
import com.mdove.passwordguard.deletelist.model.DeleteTopModel;
import com.mdove.passwordguard.deletelist.model.vm.DeletePasswordModelVM;
import com.mdove.passwordguard.deletelist.model.vm.ItemDeleteListTopVM;
import com.mdove.passwordguard.deletelist.presenter.DeleteListPresenter;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/2/14.
 */

public class DeleteListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BaseMainModel> mData;
    private DeleteListPresenter mPresenter;
    private static final int TYPE_MAIN_DELETE_PASSWORD = 0;
    private static final int TYPE_MAIN_TOP = 1;

    public DeleteListAdapter(DeleteListPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public int getItemViewType(int position) {
        BaseMainModel model = mData.get(position);
        switch (model.mType) {
            case 1: {
                if (model instanceof DeleteTopModel) {
                    return TYPE_MAIN_TOP;
                } else if (model instanceof DeletePasswordModel) {
                    return TYPE_MAIN_DELETE_PASSWORD;
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
            case TYPE_MAIN_DELETE_PASSWORD: {
                return new DeletePasswordViewHolder((ItemDeletePasswordBinding) InflateUtils.bindingInflate(parent, R.layout.item_delete_password));
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseMainModel model = mData.get(position);
        if (holder instanceof DeletePasswordViewHolder) {
            ((DeletePasswordViewHolder) holder).bind((DeletePasswordModel) model);
        }else if (holder instanceof DeleteTopViewHolder){
            ((DeleteTopViewHolder) holder).bind(new ItemDeleteListTopVM((DeleteTopModel)model));
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

    public void notifyDeleteListData(int position) {
        notifyItemChanged(position);
    }


    public class DeletePasswordViewHolder extends RecyclerView.ViewHolder {
        private ItemDeletePasswordBinding mBinding;

        public DeletePasswordViewHolder(ItemDeletePasswordBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(DeletePasswordModel model) {
            mBinding.setViewModel(new DeletePasswordModelVM(model));
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
}
