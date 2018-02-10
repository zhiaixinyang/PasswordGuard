package com.mdove.passwordguard.main.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemMainOptionBinding;
import com.mdove.passwordguard.databinding.ItemMainTopBinding;
import com.mdove.passwordguard.databinding.ItemPasswordNormalBinding;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.main.model.PasswordModel;
import com.mdove.passwordguard.main.model.handler.MainOptionHandler;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
import com.mdove.passwordguard.main.model.vm.ItemMainTopVM;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/2/9.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BaseMainModel> mData;
    private Context mContext;
    private MainPresenter mPresenter;
    private LayoutInflater mInflater;
    private static final int TYPE_MAIN_OPTION = 0;
    private static final int TYPE_MAIN_TOP = 1;
    private static final int TYPE_MAIN_PASSWORD = 2;

    public MainAdapter(Context context, MainPresenter presenter) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mPresenter = presenter;
    }

    public void setData(List<BaseMainModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void notifyPasswordData(int position) {
        notifyItemChanged(position);
    }

    @Override
    public int getItemViewType(int position) {
        BaseMainModel model = mData.get(position);
        switch (model.mType) {
            case 0: {
                return TYPE_MAIN_OPTION;
            }
            case 1: {
                if (model instanceof MainTopModel) {
                    return TYPE_MAIN_TOP;
                } else if (model instanceof PasswordModel) {
                    return TYPE_MAIN_PASSWORD;
                }
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_MAIN_OPTION: {
                return new MainOptionViewHolder((ItemMainOptionBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_option));
            }
            case TYPE_MAIN_TOP: {
                return new MainTopViewHolder((ItemMainTopBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_top));
            }
            case TYPE_MAIN_PASSWORD: {
                return new PasswordViewHolder((ItemPasswordNormalBinding) InflateUtils.bindingInflate(parent, R.layout.item_password_normal));
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseMainModel model = mData.get(position);
        if (holder instanceof MainTopViewHolder) {
            ((MainTopViewHolder) holder).bind(new ItemMainTopVM((MainTopModel) model));
        } else if (holder instanceof PasswordViewHolder) {
            ((PasswordViewHolder) holder).bind(new ItemMainPasswordVM((PasswordModel) model));
        } else if (holder instanceof MainOptionViewHolder) {
            ((MainOptionViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class MainTopViewHolder extends RecyclerView.ViewHolder {
        private ItemMainTopBinding mBinding;

        public MainTopViewHolder(ItemMainTopBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(ItemMainTopVM vm) {
            mBinding.setViewModel(vm);
        }
    }

    public class PasswordViewHolder extends RecyclerView.ViewHolder {
        private ItemPasswordNormalBinding mBinding;

        public PasswordViewHolder(ItemPasswordNormalBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(ItemMainPasswordVM vm) {
            mBinding.setViewModel(vm);
        }
    }

    public class MainOptionViewHolder extends RecyclerView.ViewHolder {
        private ItemMainOptionBinding mBinding;

        public MainOptionViewHolder(ItemMainOptionBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind() {
            mBinding.setActionHandler(new MainOptionHandler(mPresenter));
        }
    }
}
