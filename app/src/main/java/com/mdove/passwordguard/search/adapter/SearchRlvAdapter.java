package com.mdove.passwordguard.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemSearchRlvBinding;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.search.model.SearchRlvModel;
import com.mdove.passwordguard.search.model.vm.SearchRlvModelVM;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/2/15.
 */

public class SearchRlvAdapter extends RecyclerView.Adapter<SearchRlvAdapter.ViewHolder> {
    private List<BaseMainModel> mData;

    public SearchRlvAdapter() {
    }

    public void setData(List<BaseMainModel> data){
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemSearchRlvBinding) InflateUtils.bindingInflate(parent, R.layout.item_search_rlv));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BaseMainModel model = mData.get(position);
        holder.bind((SearchRlvModel) model);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemSearchRlvBinding mBinding;

        public ViewHolder(ItemSearchRlvBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
        }

        public void bind(SearchRlvModel model) {
            mBinding.setViewModel(new SearchRlvModelVM(model));
        }
    }
}
