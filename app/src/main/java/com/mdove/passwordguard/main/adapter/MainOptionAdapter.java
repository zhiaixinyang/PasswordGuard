package com.mdove.passwordguard.main.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemMainOptionBinding;
import com.mdove.passwordguard.databinding.ItemMainOptionRlvBinding;
import com.mdove.passwordguard.main.model.MainOptionInfo;
import com.mdove.passwordguard.main.model.handler.ItemMainOptionHandler;
import com.mdove.passwordguard.main.model.vm.ItemMainOptionVM;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/3/21.
 */

public class MainOptionAdapter extends RecyclerView.Adapter<MainOptionAdapter.ViewHolder> {
    private MainPresenter mPresenter;
    private List<MainOptionInfo> mData;

    public MainOptionAdapter(List<MainOptionInfo> data, MainPresenter presenter) {
        mData = data;
        mPresenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemMainOptionRlvBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_option_rlv));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMainOptionRlvBinding mBinding;

        public ViewHolder(ItemMainOptionRlvBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(MainOptionInfo mainOptionInfo) {
            mBinding.setViewModel(new ItemMainOptionVM(mainOptionInfo));
            mBinding.setActionHandler(new ItemMainOptionHandler(mPresenter));
        }
    }
}
