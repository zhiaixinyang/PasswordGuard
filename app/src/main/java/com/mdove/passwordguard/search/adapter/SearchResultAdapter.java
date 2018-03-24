package com.mdove.passwordguard.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;
import com.mdove.passwordguard.dailyself.MainDailySelfHandler;
import com.mdove.passwordguard.dailyself.MainDailySelfModel;
import com.mdove.passwordguard.databinding.ItemMainDailyselfBinding;
import com.mdove.passwordguard.databinding.ItemPasswordNormalBinding;
import com.mdove.passwordguard.databinding.ItemSearchMainDailyselfBinding;
import com.mdove.passwordguard.databinding.ItemSearchPasswordNormalBinding;
import com.mdove.passwordguard.databinding.ItemSearchRlvBinding;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.MainOptionModel;
import com.mdove.passwordguard.main.model.MainSearchModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.main.model.PasswordModel;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.search.model.SearchRlvModel;
import com.mdove.passwordguard.search.model.handle.SearchResultHandler;
import com.mdove.passwordguard.search.model.vm.SearchRlvModelVM;
import com.mdove.passwordguard.search.presenter.SearchResultPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/3/23.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BaseMainModel> mData;
    private static final int TYPE_PASSWORD = 0;
    private static final int TYPE_DAILY_SELF = 1;
    private SearchResultPresenter mPresenter;

    public SearchResultAdapter(List<BaseMainModel> data, SearchResultPresenter presenter) {
        mData = data;
        mPresenter = presenter;
    }

    @Override
    public int getItemViewType(int position) {
        BaseMainModel model = mData.get(position);
        if (model instanceof PasswordModel) {
            return TYPE_PASSWORD;
        } else if (model instanceof MainDailySelfModel) {
            return TYPE_DAILY_SELF;
        }
        return super.getItemViewType(position);
    }

    public void setData(List<BaseMainModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_PASSWORD: {
                return new PasswordViewHolder((ItemSearchPasswordNormalBinding) InflateUtils.bindingInflate(parent, R.layout.item_search_password_normal));
            }
            case TYPE_DAILY_SELF: {
                return new MainDailySelfViewHolder((ItemSearchMainDailyselfBinding) InflateUtils.bindingInflate(parent, R.layout.item_search_main_dailyself));
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseMainModel model = mData.get(position);
        if (holder instanceof PasswordViewHolder) {
            ((PasswordViewHolder) holder).bind((PasswordModel) model, position);
        } else if (holder instanceof MainDailySelfViewHolder) {
            ((MainDailySelfViewHolder) holder).bind((MainDailySelfModel) model, position);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class PasswordViewHolder extends RecyclerView.ViewHolder {
        private ItemSearchPasswordNormalBinding mBinding;

        public PasswordViewHolder(ItemSearchPasswordNormalBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(final PasswordModel model, final int position) {
            mBinding.setViewModel(new ItemMainPasswordVM(model, position));
            mBinding.setActionHandler(new SearchResultHandler(mPresenter));
        }
    }

    public class MainDailySelfViewHolder extends RecyclerView.ViewHolder {
        private ItemSearchMainDailyselfBinding mBinding;

        public MainDailySelfViewHolder(ItemSearchMainDailyselfBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(MainDailySelfModel vm, int position) {
            mBinding.setViewModel(new ItemMainDailySelfVM(vm, position));
            mBinding.setActionHandler(new SearchResultHandler(mPresenter));
        }
    }

    public void notifyFavoriteDailySelfData(int position) {
        notifyItemChanged(position);
    }
}
